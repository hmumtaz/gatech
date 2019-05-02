from Graphics import *

canvas = Window("Face",500,500)

head = Circle((250,250),100)
head.fill = Color (245,241,222)
head.draw(canvas)

eye1 = Circle((215,225),20)
eye1.fill = Color ("brown")
eye1.draw(canvas)


eye2 = Circle((285,225),20)
eye2.fill = Color ("brown")
eye2.draw(canvas)


nose = Polygon((225,275),(275,275),(250,225))
nose.fill = Color ("brown")
nose.draw(canvas)

mouth = Polygon ((210,290),(290,290),(250,310))
mouth.fill = Color (255,255,255)
mouth.draw(canvas)


hatA = Rectangle((150,170),(350,120))
hatA.fill = Color(255,255,255)
hatA.draw(canvas)

hatB = Polygon((160,120),(250,5),(340,120))
hatB.fill = Color("red")
hatB.draw(canvas)