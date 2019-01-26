(ns auto-apply-coursera.core
  (:use etaoin.api)
  (:require [etaoin.keys :as keys]))

;; NOTE: https://github.com/igrishaev/etaoin

;; TODO: silent the logging library

;; DONE Find out how the custom locations work
(def driver (firefox {:path-browser "/Applications/Firefox Developer Edition.app/Contents/MacOS/firefox"}))

(go driver "https://coursera.org/")

;; NOTE: Fill the Log-in popup
;; TODO: Convert the code to the < doto construct >

(click-el driver
          (query driver {:fn/has-text "Log In"}))

(fill driver :active "abhi18av@gmail.com" keys/enter)

(fill driver :active "harshit1893" keys/enter)


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
         "I am a recent College graduate from Computer Science degree and at that time I had my own startup so I skipped the college placements hoping that all the leads and opportunities related to my startup would really materialize. We were quite hopeful and I felt that it's worth the risk.

Unfortunately, even though we tried our best the startup failed to take off and I have been left with no source of income and no business.  Now, I'm starting to look for a job with my background and since I'm already graduated I can't actually go back to my University campus for recruitment cells.

Whatever savings I had I had invested in an MBA from the IGNOU, open university in India. I'm applying for the financial aid, in the hopes that the certificates would show my perspective employers the level of commitment I have towards my own growth and how I would be able to contribute in their organization in the same way.")


(fill-el driver
         (second (query-all driver {:fn/has-class "big-input-box"}))
         "I believe that the single most important things behind successful and, more importantly, sustainable startups are the people who deeply influence the direction of the product and create the core organizational value.

When I look back at my own startup experience, I feel that the problem was not so much with the product but with the people and the culture of the organization. Which instead of enabling people, created a toxic environment which gave rise to inefficiency and an environment full of doubt.

I'd like to complete this degree so that I'm able to manage the People cycle in my next company, enabling them to be constructive and productive in the organization and towards the product.

What I'm planning is to apply for the jobs using LinkedIn, references and via direct queries - but so far I'm finding this to be tough. Perhaps, I lack skills - but that doesn't calm the sense of urgency in my mind. I came across Financial Aid option in Coursera hoping to enroll in the courses as a normal student and complete the courses with all my efforts, engage with the course community and find like minded people.")


(click-el driver
          (second (query-all driver {:fn/has-class "finaid-radio-button"})))

(fill-el driver
         (nth (query-all driver {:fn/has-class "big-input-box"}) 2)
         "At the moment I don't have any source of income and I'd like to invest in my knowledge and my skills to land a first job and later perhaps my own startup.

I sincerely hope that the course team considers me worthy and gives me the opportunity to enroll in this course as a proper student.")


(click-el driver
          (query driver {:fn/has-class "finaid-submit-bttn"}))


