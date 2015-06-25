# cd /path/to/folder
# To change extension:
# rename 's/\.JPG$/.jpg/' *
# To rename all files in folder using bash (http://stackoverflow.com/questions/3211595/renaming-files-in-a-folder-to-sequential-numbers):
# a=1
# for i in *.jpg; do new=$(printf "%04d.jpg" "$a"); mv -- "${i}" "${new}"; let a=a+1; done
#

s0_bg_image 	= "s0_bg_image = "
s0_bg_image_pos = "s0_bg_image_pos ="
s0_bg_fill_type = "s0_bg_fill_type = "
s0_bg_color1 	= "s0_bg_color1 = "
s0_bg_color2 	= "s0_bg_color2 = "
s0_cycle_wallpapers = "s0_cycle_wallpapers = true"
s0_cycle_timeout = "s0_cycle_timeout = 10,000000"
s0_fade_duration = "s0_fade_duration = 2,000000"

for i in range(1,184):
	s0_bg_image 	=s0_bg_image+"/opt/activarsas/data/profile/backgrounds/%04d;" %(i)
	s0_bg_image_pos =s0_bg_image_pos+"0;"
	s0_bg_fill_type =s0_bg_fill_type+"0;"
	s0_bg_color1 	=s0_bg_color1+"#000000ff;"
	s0_bg_color2 	=s0_bg_color2+"#000000ff;"
	
print s0_bg_image
print s0_bg_image_pos
print s0_bg_fill_type
print s0_bg_color1
print s0_bg_color2
print s0_cycle_wallpapers
print s0_cycle_timeout
print s0_fade_duration
