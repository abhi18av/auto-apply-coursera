(use 'etaoin.api)
(require '[etaoin.keys :as k])

;; NOTE: https://github.com/igrishaev/etaoin

;; The driver comes preinstalled in mac
;; (def driver (safari))


;; DONE Find out how the custom locations work
; NOTE: For developer edition
;; /Applications/Firefox Developer Edition.app/Contents/MacOS

;(def driver (firefox {:path-browser "/Applications/Firefox.app/Contents/MacOS/firefox"}))
;; DONE Find out how the custom locations work
                                        ; NOTE: For developer edition find out how to do it
(def driver (firefox {:path-browser "/Applications/Firefox Developer Edition.app/Contents/MacOS/firefox"}))

;(def driver (firefox {:path-browser "/Applications/Firefox.app/Contents/MacOS/firefox"}))


;; NOTE: chrome

;; (def driver (chrome {:path-browser "/Applications/Chromium.app/Contents/MacOS/Chromium"}))




;;;;;;;;;;;;

;; let's perform a quick Wiki session
(go driver "https://coursera.org/")

;; TODO: Now click on the Log-In element with the following xpath
;; /html/body/div[2]/div/div/span/div/header/div[2]/div/div[1]/div/div/div[3]/ul/li[4]/a
(fill driver {:xpath "/html/body/div[2]/div/div/span/div/header/div[2]/div/div[1]/div/div/div[3]/ul/li[4]/a"} "XPath selector" keys/enter)



(query driver {:tag :button :fn/text "Log In"})



(wait-visible driver [{:id :simpleSearch} {:tag :input :name :search}])

;; search for something
(fill driver {:tag :input :name :search} "Clojure programming language")
(fill driver {:tag :input :name :search} k/enter)
(wait-visible driver {:class :mw-search-results})

;; I'm sure the first link is what I was looking for
(click driver [{:class :mw-search-results} {:class :mw-search-result-heading} {:tag :a}])
(wait-visible driver {:id :firstHeading})

;; let's ensure
(get-url driver) ;; "https://en.wikipedia.org/wiki/Clojure"

(get-title driver) ;; "Clojure - Wikipedia"

(has-text? driver "Clojure") ;; true

;; navigate on history
(back driver)
(forward driver)
(refresh driver)
(get-title driver) ;; "Clojure - Wikipedia"

;; stops Firefox and HTTP server
(quit driver)
