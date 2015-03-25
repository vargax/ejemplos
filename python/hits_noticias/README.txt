conHitsNoticias.py	--> Es script Python
output.txt			--> La salida del script
						|-> Detalla para cada candidato los criterios calculados y sus respectivos resultados

data/cache			--> Contiene los archivos .html descargados para cada criterio de búsqueda
						|-> Se pueden abrir con un navegador
						
data/salida			--> Contiene los archivos .csv con los resultados de las búsquedas
						|-> Hay un archivo por cada criterio para cada diario
						|-> Se pueden abrir con Excel para extraer los resultados
						|-> # CRITERIO 1: Ambos nombres primer apellido
						|-> # CRITERIO 2: Primer nombre primer apellido
						|-> # CRITERIO 3: Senador + primer apellido
						|-> # CRITERIO 4: Representante + primer apellido

En los archivos de resultados se coloca '0' en cada año si:
	* Algún criterio no arroja resultados 
	* Algún criterio no aplica (criterios 3 o 4)
	* En CRITERIO 1 si CRITERIO 1 == CRITERIO 2 (el candidato solo tiene un nombre)
