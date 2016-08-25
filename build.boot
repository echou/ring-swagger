(def +project+ 'erp/ring-swagger)
(def +version+ "0.23.0-SNAPSHOT")

(task-options!
  pom {:project +project+
       :description "Swagger Spec for Ring Apps (echou)"
       :version +version+}
  push {:gpg-sign false
        :repo (if (.endsWith +version+ "SNAPSHOT") "snapshots" "releases")})
  
(set-env!
  :source-paths #{"src"}
  :dependencies '[[cheshire "5.6.3"]
                  [slingshot "0.12.2"]
                  [metosin/ring-http-response "0.8.0"]
                  [metosin/schema-tools "0.9.0"]
                  [prismatic/schema "1.1.3"]
                  [prismatic/plumbing "0.5.3"]
                  [metosin/scjsv "0.3.0"]
                  [clj-time "0.12.0"]
                  [org.tobereplaced/lettercase "1.0.0"]
                  [potemkin "0.4.3"]
                  [frankiesardo/linked "1.2.7"]])

(deftask build
  []
  (comp
    (pom)
    (jar)
    (sift :include #{#"\.jar$"})
    (target)))
    
(deftask deploy
  []
  (comp (build) (install) (push)))
