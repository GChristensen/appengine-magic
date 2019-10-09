(ns appengine-magic.jetty
  (:use [appengine-magic.servlet :only [servlet]])
  (:import org.eclipse.jetty.server.handler.ContextHandlerCollection
           [org.eclipse.jetty.server Server]
           [javax.servlet DispatcherType]
           [org.eclipse.jetty.servlet ServletContextHandler ServletHolder FilterHolder]
           [java.util EnumSet]
           [org.eclipse.jetty.apache.jsp JettyJasperInitializer]
           [org.eclipse.jetty.util.component AbstractLifeCycle]
           [org.apache.jasper.runtime JspFactoryImpl]
           [javax.servlet.jsp JspFactory]))


(defn- proxy-multihandler
  "Returns a Jetty Handler implementation for the given map of relative URLs to
   handlers. Each handler may be a Ring handler or an HttpServlet instance."
  [filters all-handlers]
  (let [all-contexts (ContextHandlerCollection.) 
        context (ServletContextHandler. all-contexts "/" ServletContextHandler/SESSIONS)]

    (JspFactory/setDefaultFactory (JspFactoryImpl.))
    (.addBean context (proxy [AbstractLifeCycle] []
                        (doStart []
                          (.onStartup (JettyJasperInitializer.) nil (.getServletContext context)))))

    (doseq [[url filter-objs] filters]
      (let [filter-objs (if (sequential? filter-objs) filter-objs [filter-objs])]
        (doseq [filter-obj filter-objs]
          (.addFilter context (FilterHolder. filter-obj) url (EnumSet/allOf DispatcherType)))))

    (doseq [[relative-url url-handler] all-handlers]
      (.addServlet context (ServletHolder. (if (string? url-handler)
                                             (Class/forName url-handler)
                                             url-handler))
                   relative-url))
    all-contexts))


(defn #^Server start [filter-map servlet-map &
                      {:keys [port join?] :or {port 8080 join? false}}]
  (let [server (Server. port)]
    (doto server
      (.setHandler (proxy-multihandler filter-map servlet-map))
      (.start))
    (when join? (.join server))
    server))


(defn stop [#^Server server]
  (.stop server))
