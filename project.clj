(defproject appengine-magic "0.5.1-SNAPSHOT"
  :description "Google App Engine library for Clojure."
  :url "https://github.com/gcv/cupboard"
  :min-lein-version "2.0.0"
  :repositories {"releases" "http://appengine-magic-mvn.googlecode.com/svn/releases/"
                 "snapshots" "http://appengine-magic-mvn.googlecode.com/svn/snapshots/"}
  :exclusions [org.clojure/clojure]
  :dependencies [[ring/ring-core "1.7.1"]
                 ;[ring/ring-jetty-adapter "1.7.1"]
                 [org.eclipse.jetty/jetty-server "9.4.14.v20181114"]
                 [org.eclipse.jetty/jetty-servlet "9.4.14.v20181114"]
                 ;; App Engine supporting essentials
                 [commons-io "2.4"]
                 [commons-codec "1.7"]
                 [commons-fileupload "1.3.2"]
                 [org.apache.commons/commons-exec "1.1"]
                 ;; App Engine administrative interface support
                 [commons-el "1.0"]
                 [javax.servlet.jsp/jsp-api "2.1"]
                 [javax.activation/activation "1.1.1"]
                 [org.eclipse.jetty/apache-jsp "9.4.14.v20181114"]
                 [org.apache.taglibs/taglibs-standard-spec "1.2.5"]
                 [org.apache.taglibs/taglibs-standard-impl "1.2.5"]
                 ;; main App Engine libraries
                 [com.google.appengine/appengine-api-1.0-sdk "1.9.76"]
                 [com.google.appengine/appengine-api-labs "1.9.76"]
                 [com.google.appengine/appengine-api-stubs "1.9.76"]
                 [com.google.appengine/appengine-local-runtime "1.9.76"]
                 [com.google.appengine/appengine-local-runtime-shared "1.9.76"]
                 [com.google.appengine/appengine-testing "1.9.76"]
                 [com.google.appengine/appengine-tools-sdk "1.9.76"]
                 ;; for the Leiningen plugin
                 [me.raynes/fs "1.4.5"]])
