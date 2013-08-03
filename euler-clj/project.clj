(defproject euler-clj "0.1.0-SNAPSHOT"
  :description "My Project Euler solutions in Clojure."
  :url "http://projecteuler.net/"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies   [[org.clojure/clojure "1.5.1"]
                   [org.clojure/math.numeric-tower "0.0.2"]]
  :uberjar-name   "euler.jar"
  :main           euler-clj.core
  :jvm-opts       ["-server" "-d64" "-XX:+UseConcMarkSweepGC" "-XX:+UseParNewGC"]
  :javac-options  ["-target" "1.7" "-source" "1.7" "-Xlint:-options"])
