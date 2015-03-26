capture log close
clear
cd "C:\Users\Camilo\Documents\Uniandes\200720\Econometría I\Taller3.EconometríaI"
log using heteroscedasticidad2.smcl, replace
use SLEEP75

* -----
* a)
* -----
gen malerz=(male)^0.5

gen tsleep=sleep/malerz
gen ttotwrk=totwrk/malerz
gen teduc=educ/malerz
gen tage=age/malerz
gen tagesq=agesq/malerz
gen tyngkid=yngkid/malerz

* -----
* b)
* -----
reg sleep totwrk educ age agesq yngkid male
reg tsleep ttotwrk teduc tage tagesq tyngkid if malerz==1

* -----
* c)
* -----
test ttotwrk teduc tage tagesq tyngkid
