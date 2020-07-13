(ns app.subs
  (:require [re-frame.core :as rf]))

(rf/reg-sub
 :vocab-lists
 (fn [db _]
   (:vocab-lists db)))

(rf/reg-sub
 :exercise
 (fn [db _]
   (:exercise db)))

(rf/reg-sub
 :vocab
 :<- [:exercise]
 (fn [exercise _]
   (:vocab exercise)))

(rf/reg-sub
 :exercise-in-progress?
 :<- [:exercise]
 (fn [exercise _]
   (:exercise-in-progress? exercise)))

(rf/reg-sub
 :exercise-id
 :<- [:exercise]
 (fn [exercise _]
   (:exercise-id exercise)))

(rf/reg-sub
 :exercise-option
 :<- [:exercise]
 (fn [exercise _]
   (:exercise-option exercise)))

(rf/reg-sub
 :active-exercise?
 :<- [:exercise-id]
 :<- [:exercise-option]
 (fn [[exercise-option exercise-id] _]
   (boolean (and exercise-id exercise-option))))

(rf/reg-sub
 :show-unanswered-cloud?
 :<- [:exercise-option]
 (fn [exercise-option _]
   (= exercise-option :word-cloud)))

(rf/reg-sub
 :unanswered-vocab
 :<- [:vocab]
 (fn [vocab _]
   (:unanswered vocab)))

(rf/reg-sub
 :incorrect-vocab
 :<- [:vocab]
 (fn [vocab _]
   (:incorrect vocab)))

(rf/reg-sub
 :answered-vocab
 :<- [:vocab]
 (fn [vocab _]
   (:answered vocab)))

(rf/reg-sub
 :correct-answers
 :<- [:exercise-option]
 :<- [:unanswered-vocab]
 (fn [[exercise-option unanswered-vocab] _]
   (case exercise-option
     :word-cloud unanswered-vocab
     :prompt (take 1 unanswered-vocab)
     unanswered-vocab)))

(rf/reg-sub
 :prompt-text
 :<- [:exercise-option]
 :<- [:correct-answers]
 (fn [[exercise-option correct-answers] _]
   (when (= exercise-option :prompt)
     (-> correct-answers first second))))

(rf/reg-sub
 :page
 (fn [db _]
   (:page db)))
