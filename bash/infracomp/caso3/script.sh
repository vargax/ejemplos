# El número de solicitudes a realizar en cada iteración
solicitudes=400
protocoloSeguro='true'
# Limpiando los archivos de resultados
echo ", Caso 3 - Infraestructura Computacional" > "resultados$protocoloSeguro.csv"
echo ", $solicitudes solicitudes" >> "resultados$protocoloSeguro.csv"

echo "Caso 3 InfraComp - Output Servidor" > "servidor$protocoloSeguro.csv"
echo "Caso 3 InfraComp - Output Cliente" > "cliente$protocoloSeguro.txt"

# Ciclos del experimento
for threads in 1 2 8 16
do
	for retardo in 20 50 100
	do
		for i in {1..3}
		do
			java -jar servidor.jar $threads $protocoloSeguro >> "servidor$protocoloSeguro.csv" &
			server=$!
			echo "PID Servidor = $server"
			java -jar cliente.jar $solicitudes $retardo >> "cliente$protocoloSeguro.txt" &
			client=$!
			echo "PID Cliente = $client"
			espera=$(( 70 + ((solicitudes * retardo)/500) )) # Cuadrándolo a mano!
			echo "Esperando por $espera segundos"
			sleep $espera
			echo ", Threads, Retardo, Iteración" >> "resultados$protocoloSeguro.csv"
			echo ",$threads, $retardo, $i" >> "resultados$protocoloSeguro.csv"
			ps -o pcpu,min_flt $server >> "resultados$protocoloSeguro.csv"
			kill $client
			kill $server
		done
	done
done
