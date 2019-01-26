(ns auto-apply-coursera.core
  (:use etaoin.api)
  (:require [etaoin.keys :as keys]
            [clojure.edn :as edn]))

;; NOTE: https://github.com/igrishaev/etaoin

;; TODO: silent the logging library

;; DONE Find out how the custom locations work
(def driver (firefox {:path-browser "/Applications/Firefox Developer Edition.app/Contents/MacOS/firefox"}))

(go driver "https://coursera.org/")

;; TODO: Add proper wait times all along the flow

;; TODO: Parallelize the application mechanism


;; NOTE: Fill the Log-in popup
;; TODO: Convert the code to the < doto construct >

(click-el driver
          (query driver {:fn/has-text "Log In"}))


;; Read the credentials from a different file
(def credentials
  (edn/read-string
   (slurp "./_secrets/secrets.edn")))

(def email
  (:email credentials))

(def password
  (:password credentials))


(fill driver :active email keys/enter)

(fill driver :active password keys/enter)


;; URL of specialization
;; https://www.coursera.org/specializations/human-resource-management

;; TODO: Find a list of all courses in a specializations
;; Click on < Continue >
;; Click on < Audit >


;; URL of a course
;; https://www.coursera.org/learn/managing-human-resources


(go driver "https://www.coursera.org/learn/managing-human-resources")

;; Click on < Financial Aid Available >


(click-el driver
          (query driver {:fn/has-text "Financial Aid Available"}))



;; Click on < Continue To Application >


(click-el driver
          (query driver {:fn/has-text "Continue to the application"}))



;; On the application start screen click on the buttons

(click-el driver
          (first (query-all driver {:fn/has-class "rc-Checkbox"})))

(click-el driver
          (second (query-all driver {:fn/has-class "rc-Checkbox"})))

;; Fill text in the following field


(fill-el driver
         (query driver {:fn/has-class "accept-terms-field"})
         "I agree to the terms above")



(click-el driver
          (query driver {:fn/has-class "continue-button"}))

;; NOTE: Now we are the main application page

(back driver)

;; TODO: Find a way to select from dropdown menu
;; Need to execute this twice
(fill driver {:id "finaid-educationalBackground"} "College degree")




;; Annual Income
(fill-el driver
         (first (query-all driver {:fn/has-class "finaid-rigor-num-input"}))
         "0")

;; Employment status
(fill driver {:id "finaid-employmentStatus"} "Unemployed")

;; How much can you afford to pay


(fill-el driver
         (second (query-all driver {:fn/has-class "finaid-rigor-num-input"}))
         "0")



(fill driver {:id "finaid-per-period"} "per course")


;; Moving on to the big-input-box


(fill-el driver
         (first (query-all driver {:fn/has-class "big-input-box"}))
         "My reason for the application")


(fill-el driver
         (second (query-all driver {:fn/has-class "big-input-box"}))
         "How this course helps me achieve my goals")

(fill-el driver
         (nth (query-all driver {:fn/has-class "big-input-box"}) 2)
         "Can't pay a low interest loan")


(click-el driver
          (second (query-all driver {:fn/has-class "finaid-radio-button"})))


(click-el driver
          (query driver {:fn/has-class "finaid-submit-bttn"}))
