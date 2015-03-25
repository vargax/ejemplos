# -*- coding: UTF-8 -*-
'''
Created on 22/03/2013

@author: cvargasc
'''
##########################
## IMPORTS
##########################
import urllib.request
import urllib.parse
import os
import shutil
from bs4 import BeautifulSoup

##########################
## CONSTANTES
##########################
RUTA_ENTRADA = 'data/entrada.csv'
DIRECTORIO_SALIDA = 'data/salida'
DIRECTORIO_CACHE = 'data/cache'

EL_TIEMPO = '_ElTiempo'
EL_ESPECTADOR = '_ElEspectador'

AÑO_INICIAL = 1997
AÑO_FINAL = 2011
##########################
## FUNCIONES
##########################
def descargar_html(url,ruta):    
    html = urllib.request.urlopen(url)
    file = open(ruta,'wb')
    file.write(html.read())
    html.close()
    file.close()
    
def escribir_resultados(cadena,llave_archivo_salida,diccionario_resultados):
    #Generando la cadena de salida
    for año in range(AÑO_INICIAL,AÑO_FINAL+1):
        cadena+=','+str(diccionario_resultados.get(año,'0'))
    #Escribir los resultados
    cadena+='\n'
    salidas[llave_archivo_salida].write(cadena)
    print(diccionario_resultados)

def buscar_ElTiempo(criterio,a2011=0):
    criterio = '"'+criterio+'"'
    criterio = urllib.parse.quote_plus(criterio)
    criterio = criterio+'&a=2011' if a2011 else criterio
    print('  Buscando en El Tiempo '+criterio)
    url = 'http://www.eltiempo.com/archivo/buscar?q='+criterio 
    ruta = DIRECTORIO_CACHE+'/ElTiempo/'+criterio+'.html'
    if not os.path.exists(ruta): descargar_html(url,ruta)
    return ruta

def parser_ElTiempo(ruta_html_file,a2011=0): # Desde el 97 a Agosto del 2011    
    # Diccionario para almacenar los resultados
    resultados = {}
    # Parseando código html
    html = open(ruta_html_file)
    soup = BeautifulSoup(html)
    filtro_fecha = soup.find(id="archivo-filtro-fecha")
    
    if not a2011:
        # HASTA EL AÑO 2010
        try:
            años = filtro_fecha.find_all('li')
            for año in años:
                cadena = año.get_text()
                cadena = ' '.join(cadena.split())
                if not '2000 (1.990)' in cadena:
                    cadena = cadena.replace('(', '')
                    cadena = cadena.replace(')', '')
                    datos = cadena.split(' ')
                    resultados[int(datos[0])] = int(datos[1].replace('.',''))
        except AttributeError:
            pass
    else:
        # PARA ENERO - AGOSTO DEL 2011
        try:
            meses = filtro_fecha.find_all('li')
            for mes in meses:
                cadena = mes.get_text()
                cadena = ' '.join(cadena.split())
                cadena = cadena.replace('(', '')
                cadena = cadena.replace(')', '')
                datos = cadena.split(' ')
                if datos[0] in ('Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto'):
                    resultados[datos[0]] = int(datos[1].replace('.',''))
        except AttributeError:
            pass
        total = 0
        for mes,dato in resultados.items():
            total += dato
        return total
        
#     if len(resultados) == 0: print('    !! No se han encontrado resultados en el archivo '+ruta_html_file)
    return resultados

def buscar_ElEspectador(criterio):
    criterio = '"'+criterio+'"'
    print('  Buscando en El Espectador '+criterio)
    criterio = urllib.parse.quote(criterio)
    url = 'http://www.elespectador.com/search/apachesolr_search/'+criterio
    ruta = DIRECTORIO_CACHE+'/ElEspectador/'+criterio+'.html'
    if not os.path.exists(ruta): descargar_html(url,ruta)
    return ruta

def parser_ElEspectador(ruta_html_file):
    # Diccionario para almacenar los resultados
    resultados = {}
    # Parseando código html
    html = open(ruta_html_file)
    soup = BeautifulSoup(html)
    item_list = soup.find_all("a", { "class" : "apachesolr-facet" })
    for item in item_list:
        cadena = item.get_text()
        cadena = cadena.replace('(', '')
        cadena = cadena.replace(')', '')
        datos = cadena.split(' ')
        try:
            resultados[int(datos[0])] = int(datos[1].replace('.',''))
            # CHAMBONEANDO para recuperar los hits de Enero a Agosto del 2011!!
            if int(datos[0]) == 2011: 
                link = item.get('href')
                
                if not os.path.exists(ruta_html_file.replace('.','-2011.')): 
                    descargar_html('http://www.elespectador.com'+link,ruta_html_file.replace('.','-2011.'))
                a2011 = open(ruta_html_file.replace('.','-2011.'))
                a2011 = BeautifulSoup(a2011)
                a2011 = a2011.find_all("a", { "class" : "apachesolr-facet" })
                
                total2011 = 0
                for item2011 in a2011:                    
                    cadena2011 = item2011.get_text()
                    cadena2011 = cadena2011.replace('(', '')
                    cadena2011 = cadena2011.replace(')', '')
                    datos2011 = cadena2011.split(' ')
                    if len(datos2011) == 3 and int(datos2011[1]) == 2011 and datos2011[0] in ('Enero','Febrero','Marzo','Abril','Mayo','Junio','Julio','Agosto'): 
                        total2011 += int(datos2011[2])
                resultados[2011] = total2011
        except ValueError:
            pass
#     if len(resultados) == 0: print('    !! No se han encontrado resultados en el archivo '+ruta_html_file)
#     print ('El Espectador: ',resultados)
    return resultados
    
##########################
## SCRIPT
##########################
#Cargar el archivo de entrada
entrada = open(RUTA_ENTRADA, encoding='utf-8')
#Generar los archivos de salida
if os.path.exists(DIRECTORIO_SALIDA): shutil.rmtree(DIRECTORIO_SALIDA)
os.mkdir(DIRECTORIO_SALIDA)
salidas={}
for crit in ('crit1','crit2','crit3','crit4'):
    for diario in (EL_TIEMPO,EL_ESPECTADOR):
        salida = open(DIRECTORIO_SALIDA+'/'+crit+diario+'.csv','w')
        encabezado = 'Codigo,Nombre'
        for año in range(AÑO_INICIAL,AÑO_FINAL+1):
            encabezado+=','+str(año)
        encabezado+='\n'
        salida.write(encabezado)
        salidas[crit+diario] = salida

#Crear los directorios para cache
if not os.path.exists(DIRECTORIO_CACHE):
    os.mkdir(DIRECTORIO_CACHE)
    os.mkdir(DIRECTORIO_CACHE+'/'+EL_TIEMPO)
    os.mkdir(DIRECTORIO_CACHE+'/'+EL_ESPECTADOR)

#Para cada candidato en el archivo de entrada:
for candidato in entrada: 
    candidato = candidato.rstrip()
    
    # Parseando información del candidato [codigo,nombre1,nombre2,apellido1,representante,senador,mujer]
    info_candidato = candidato.split(',')
    for i in range(len(info_candidato)):
        info_candidato[i] = info_candidato[i].strip()
        info_candidato[i] = info_candidato[i].lower()
    
    codigo, primer_nombre, segundo_nombre, primer_apellido  = info_candidato[0], info_candidato[1], info_candidato[2],info_candidato[3]
    fue_senador, fue_representante, es_mujer = int(info_candidato[4]), int(info_candidato[5]), int(info_candidato[6])
    nombre = (primer_nombre+(' '+segundo_nombre+' ' if segundo_nombre != '' else ' ')+primer_apellido).title()
    print("Procesando "+nombre.upper()+" como " +
          ("SENADOR" if fue_senador else '') + 
          (' y ' if fue_senador and fue_representante else '') + 
          ("REPRESENTANTE" if fue_representante else ''))
    
    criterios = {}
    #Calcular los criterios de busqueda
    criterios['crit1'] = nombre.lower()                                                  # CRITERIO 1: Ambos nombres primer apellido
    criterios['crit2'] = primer_nombre + ' ' + primer_apellido                           # CRITERIO 2: Primer nombre primer apellido
    
    if criterios['crit1'] == criterios['crit2'] : criterios['crit1'] = ''
    
    criterios['crit3'] = ("senadora " if es_mujer else "senador ") +  primer_apellido if fue_senador else '' # CRITERIO 3: Senador + primer apellido
    criterios['crit4'] = "representante " + primer_apellido if fue_representante else '' # CRITERIO 4: Representante + primer apellido
    
    #Para cada criterio:
    for llave,criterio in criterios.items(): # Llave es el nombre del criterio (crit1, crit2, etc), criterio es el criterio de búsqueda
        #Realizar la búsqueda, parsear los resultados, escribirlos
        cadena = codigo+','+nombre
        if criterio != '':
            ruta_ElTiempo = buscar_ElTiempo(criterio)           # Busco en El Tiempo
            resultado_ElTiempo = parser_ElTiempo(ruta_ElTiempo) # Parseo los resultados de El Tiempo
            
            ruta_ElTiempo_2011 = buscar_ElTiempo(criterio,1)    # Busco en El Tiempo para el 2011
            resultado_ElTiempo[2011] = parser_ElTiempo(ruta_ElTiempo_2011,1)  # El número de hits entre Enero y Agosto del 2011
            
            ruta_ElEspectador = buscar_ElEspectador(criterio)                 # Busco en El Espectador
            resultado_ElEspectador = parser_ElEspectador(ruta_ElEspectador)   # Parseo los resultados de El Espectador
        else:
            resultado_ElTiempo,resultado_ElEspectador = {},{}   # Si el criterio es '' genero dos diccionarios vacíos
        
        escribir_resultados(cadena,llave+EL_TIEMPO,resultado_ElTiempo)          # Escribo los resultados de El Tiempo
        escribir_resultados(cadena,llave+EL_ESPECTADOR,resultado_ElEspectador)  # Escribo los resultados de El Espectador

#Cerrar los archivos de entrada y salida
entrada.close()
for llave,salida in salidas.items():
    salida.close()