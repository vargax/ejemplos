# -*- coding: UTF-8 -*-
'''
Requiere librerias pprint y matplotlib
pprint => Instalar con pip: aptitude install python-pip && pip install pprint
matplotlib => Instalar desde repositorio: aptitude install python-matplotlib
'''
# IMPORTS
import pprint
import random
import time
import matplotlib.pyplot as plt

# FUNCIONES AUXILIARES
def floatRange(inicio, fin, incremento):
	while inicio <= fin:
		yield inicio
		inicio += incremento

# FUNCIONES OBJETIVO
def f1(x1,x2):
	return pow(x1,2) + pow(x2,2)
	
def f2(x1,x2):
	return pow((x1-1),2) + pow(x2,2)

# INICIO SCRIPT
pp = pprint.PrettyPrinter()

print "SUMAS PONDERADAS"
print "peso : (x1,x2,f)"
def sumasPonderadas(w,x1,x2):
	return w*f1(x1,x2)+(1-w)*f2(x1,x2)

minimos = {}
for w in floatRange(0,1,0.05):
	minimo = (0,0,float('inf'))
	for x1 in floatRange(-2,2,0.05):
		for x2 in floatRange(-2,2,0.05):
			candidato = (x1,x2,sumasPonderadas(w,x1,x2))
			if candidato[2] < minimo[2]:
				minimo = candidato
	minimos["{0:.2f}".format(w)] = ("{0:.2f}".format(minimo[0]),"{0:.2f}".format(minimo[1]),"{0:.2f}".format(minimo[2]))
	
pp.pprint(minimos)
				
print "\n ALGORITMO EVOLUTIVO"
# TAREAS:
# Generar una población
pobl_max = 1500
pobl_mutacion = int(round(0.05*pobl_max)) # Qué porcentaje de la población muta?
generaciones = 50

plt.ion()
plt.show()

Xs = []
for x in floatRange(-2,2,0.01):
	Xs.append(x)

poblacion = []
for i in range(pobl_max):
	x1rand = random.choice(Xs)
	x2rand = random.choice(Xs)
	poblacion.append((x1rand,x2rand))

frontera = []
for i in poblacion:
	resultadof1 = f1(i[0],i[1])
	resultadof2 = f2(i[0],i[1])
	frontera.append((resultadof1, resultadof2))	


fitness = []
for g in range(generaciones):
	
	# Pintando la población
	plt.clf()
	plt.subplot(211)
	plt.scatter(*zip(*poblacion))
	# Pintando la frontera
	plt.subplot(212)
	plt.scatter(*zip(*frontera))
	
	plt.draw()
	time.sleep(0.05)
	
	print "  Generación ", g
	# Evaluar población en las funciones objetivo (fitness)
	frontera = []
	fitness = []
	for i in poblacion:
		resultadof1 = f1(i[0],i[1])
		resultadof2 = f2(i[0],i[1])
		frontera.append((resultadof1, resultadof2))
		fitness.append((i, resultadof1, resultadof2))

	# Seleccionar a los mejores => La mitad de la población con mejor fitness
	mejores = [((0,0), float('inf'), float('inf')) for i in range(pobl_max/2)]
	for i in fitness:
		for j in range(len(mejores)):
			if i[1] < mejores[j][1] and i[2] < mejores[j][2]:
				mejores[j] = i;
				break
	#print mejores
	
	# Combinar los mejores (crossover) 
	poblacion = []
	for i in mejores:
		if i[1] == float('inf') or i[2] == float('inf'):
			break
		# => La mitad seleccionada pasa a la siguiente generación
		poblacion.append(i[0])
		# => Para cada uno se selecciona un candidato de forma aleatoria dentro de los seleccionados:	
		candidato = random.choice(mejores)
		while candidato[1] == float('inf') or candidato[2] == float('inf'):
			candidato = random.choice(mejores)
		# => Se genera un hijo (x1,x2') donde x2' = x2 del candidato	
		poblacion.append((i[0][0],candidato[0][1]))
		# => La nueva población son los padres y sus hijos

	# Mutarlos (mutation) => multiplicar cromosomas por un factor aleatorio entre 0 y 2
	for i in range(pobl_mutacion):
		randIndex = int(round(random.random()*(len(poblacion)-1)))
		mutante = poblacion[randIndex]
		
		mutacionX1 = random.random()*2
		mutacionX2 = random.random()*2
		mutante = (mutante[0]*mutacionX1, mutante[1]*mutacionX2)
		
		poblacion[randIndex] = mutante

print "\n Fitness"
print "x1 x2 f1 f2"
for i in fitness:
	#pass
	print "{0:.3f}".format(i[0][0]),"{0:.3f}".format(i[0][1]),"{0:.3f}".format(i[1]),"{0:.3f}".format(i[2])

