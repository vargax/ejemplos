----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    07:20:35 11/03/2011 
-- Design Name: 
-- Module Name:    juegoBolita - Behavioral 
-- Project Name: 
-- Target Devices: 
-- Tool versions: 
-- Description: 
--
-- Dependencies: 
--
-- Revision: 
-- Revision 0.01 - File Created
-- Additional Comments: 
--
----------------------------------------------------------------------------------
library IEEE;
use IEEE.STD_LOGIC_1164.ALL;
use IEEE.std_logic_unsigned.all;
use ieee.std_logic_arith.all;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity juegoBolita is
-- El reloj aqu definido debe dar un flanco de subida cada
-- centsima de segundo. 
	Port(	clk 			: in  STD_LOGIC;
         reset 		: in  STD_LOGIC;
         empezar 		: in  STD_LOGIC;			
         derecha 		: in  STD_LOGIC;
         izquierda	: in  STD_LOGIC;
         filasOut 	: out STD_LOGIC_VECTOR (7 downto 0);
         columnasOut	: out STD_LOGIC_VECTOR (7 downto 0);
			puntajeOut	: out STD_LOGIC_VECTOR (3 downto 0)
	);
end juegoBolita;

architecture Behavioral of juegoBolita is
	type estados is (rst,inicio,jugando,victoria,derrota);
	signal estado : estados;
	
	type estadosBoton is (presionado,liberado);
	signal boton : estadosBoton;
	
	signal filas			: STD_LOGIC_VECTOR(2 downto 0);
	signal columnas		: STD_LOGIC_VECTOR(2 downto 0);
	signal columnaInicio : STD_LOGIC_VECTOR(2 downto 0);
	
	signal filaBolita		: STD_LOGIC_VECTOR(2 downto 0);
	signal columnaBolita : STD_LOGIC_VECTOR(2 downto 0);
	signal columnaJugador: STD_LOGIC_VECTOR(2 downto 0);
	
	signal puntaje	: STD_LOGIC_VECTOR(3 downto 0);
	signal avance	: STD_LOGIC_VECTOR(4 downto 0);
begin
	-- Mquina de Estados
	process(clk,reset,empezar,derecha,izquierda,filaBolita,columnaBolita,columnaJugador,puntaje,avance)
	begin													-- Falta meter las seales de los estados! (estado, boton)
		if reset = '1' then
			estado	<= rst;
			boton		<= liberado;
			columnaBolita 	<= "000";
			filaBolita		<= "000";
			columnaJugador	<= "100";
			puntaje	<= "0000";
			avance	<= "00000";
		elsif clk'event and clk = '1' then
			case estado is
				when rst =>
					if empezar = '1' then
					estado <= inicio;
					end if;
				when inicio =>
					puntaje <= "0000";
					columnaBolita 	<= columnaInicio;
					filaBolita		<= "111";
					estado <= jugando;
				when jugando =>
					-- Proceso para mover la bolita del jugador
					if boton = liberado then
						if derecha = '1' then		-- Muevo la bolita del jugador a la derecha
							boton <= presionado;
							columnaJugador <= columnaJugador + 1;
						elsif izquierda = '1' then	-- Muevo la bolita del jugador a la izquierda
							boton <= presionado;
							columnaJugador <= columnaJugador - 1;
						end if;
					elsif derecha = '0' and izquierda = '0' then
						boton <= liberado;
					end if;
					-- Proceso para la caida de la bolita
					avance <= avance + 1;
					if avance = "00000" then
						filaBolita <= filaBolita - 1;
					elsif filaBolita = "000" then
						if columnaBolita = columnaJugador then
							if puntaje = "1001" then
								estado <= victoria;
							else
								puntaje <= puntaje + 1;
							end if;
						else
							estado <= derrota;
						end if;
					end if;
				when victoria =>
					if empezar = '1' then
						estado <= inicio;
					end if;
				when derrota  =>
					if empezar = '1' then
						estado <= inicio;
					end if;
			end case;
		end if;
	end process;
	-- Generando columnaInicio aleatoria
	process(clk,reset,empezar)
	begin
		if reset = '1' then
			columnaInicio <= "000";
		elsif empezar = '1' and clk'event and clk = '1' then
				columnaInicio <= columnaInicio + 1;
		end if;
	end process;
	-- Dibuja en el display
	process(clk, reset, estado, columnaJugador, columnaBolita, filaBolita)
	begin
		if reset = '1' then
			filas 	<= "000";
			columnas	<= "100";
		elsif clk'event and clk = '1' then
			
		end if;
	end process;
	-- Transformando filas y columnas para la salida (BLOQUE COMBINACIONAL)
	process(reset,estado,filas,columnas)
	begin
		if reset = '1' then
			columnasOut <= "00000000";
			filasOut		<= "00000000";
			puntajeOut	<= "0000";
		else
			case estado is
				when victoria =>
					columnasOut <= "11111111";
					filasOut <= "10000001";
				when derrota =>
					columnasOut <= "11111111";
					filasOut		<= "11111111";
				when others =>
					puntajeOut <= puntaje;
					case columnas is
						when "000" => columnasOut <= "10000000";
						when "001" => columnasOut <= "01000000";
						when "010" => columnasOut <= "00100000";
						when "011" => columnasOut <= "00010000";
						when "100" => columnasOut <= "00001000";
						when "101" => columnasOut <= "00000100";
						when "110" => columnasOut <= "00000010";
						when "111" => columnasOut <= "00000001";
						when others => columnasOut <= "00000000";
					end case;
					case filas is
						when "000" => filasOut <= "10000000";
						when "001" => filasOut <= "01000000";
						when "010" => filasOut <= "00100000";
						when "011" => filasOut <= "00010000";
						when "100" => filasOut <= "00001000";
						when "101" => filasOut <= "00000100";
						when "110" => filasOut <= "00000010";
						when "111" => filasOut <= "00000001";
						when others => filasOut <= "00000000";
					end case;
			end case;
		end if;
	end process;
end Behavioral;