(ns auto-apply-coursera.sparkledriver
  (:require
  [sparkledriver.browser :refer [with-browser make-browser fetch!]]
  [sparkledriver.element :refer [find-by-xpath* text]]))


;; (with-browser [browser (make-browser)]
;;   (-> (fetch! browser "http://clojure.org")
;;       (find-by-xpath* "//div[@class='clj-intro-message']/p")
;;       (nth 2)
;;       text))


(with-browser [browser (make-browser :headless false)]
  (-> (fetch! browser "http://coursera.org")))



(with-chrome {} driver
  (with-wait 3
    (go driver "http://site.com")
    (click driver {:id "search_button"})))


