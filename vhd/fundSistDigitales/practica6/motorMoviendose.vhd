----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:38:55 10/23/2011 
-- Design Name: 
-- Module Name:    motorMoviendose - Behavioral 
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
--use IEEE.std_logic_unsigned.all;
use ieee.std_logic_arith.all;

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity motorMoviendose is
    Port ( clock : in  STD_LOGIC;
           reset : in  STD_LOGIC;
           motor0 : out  STD_LOGIC;
           motor1 : out  STD_LOGIC;
           motor2 : out  STD_LOGIC;
           motor3 : out  STD_LOGIC;
           parlante : out  STD_LOGIC);
end motorMoviendose;

architecture Behavioral of motorMoviendose is
	-- Definiendo estados Motor
	type estadosMotor is (p0,p1,p2,p3,p4,p5,p6,p7);
	signal estadoActual : estadosMotor;
	signal estadoAnterior : estadosMotor;
	-- Definiendo señales internas
	signal contadorMotor			: STD_LOGIC_VECTOR (4 downto 0) := "00000";
	signal cuantosPasos			: STD_LOGIC_VECTOR (4 downto 0) := "10100";
	signal derecha					: STD_LOGIC := '1';
	
begin
	process(reset)
	begin
		if (reset = '1') then
			derecha <= '1';
			contadorMotor <= "00000";
			cuantosPasos <= "10100";

			estadoActual 	<= p0;
			estadoAnterior <= p0;
			
			parlante <= '0';
			motor0 <= '0';
			motor1 <= '0';
			motor2 <= '0';
			motor3 <= '0';
		end if;
	end process;
	---------------------------------------------
	-- Máquina de estados Motor
	---------------------------------------------
	process(clock,reset,derecha,cuantosPasos,estadoAnterior,contadorMotor)
	-- Responsable de calcular 'estadoActual'
	-- Controla 'parlante' y 'contadorMotor'
	begin
		if (reset = '0' and clock'event and clock = '1') then 	-- Si detectó un flanco de subida en el reloj...
			if (contadorMotor = cuantosPasos) then		-- Si ya terminé coloco mi contador en cero
				contadorMotor <= "00000";
				parlante <= '0';
				estadoActual <= estadoAnterior;
			else
				contadorMotor <= contadorMotor + 1;	-- avanzo el contador...
				parlante <= '1';							-- hago sonar el parlante...
				if derecha = '1' then					-- y, si tengo que girar a la derecha...
					case estadoAnterior is
						when p0 =>
							estadoActual <= p1;
						when p1 =>
							estadoActual <= p2;
						when p2 =>
							estadoActual <= p3;
						when p3 =>
							estadoActual <= p4;
						when p4 =>
							estadoActual <= p5;
						when p5 =>
							estadoActual <= p6;
						when p6 =>
							estadoActual <= p7;
						when p7 =>
							estadoActual <= p0;
					end case;
				elsif derecha = '0' then				-- pero, si tengo que girar a la izquierda...
					case estadoAnterior is
						when p0 =>
							estadoActual <= p7;
						when p1 =>
							estadoActual <= p0;
						when p2 =>
							estadoActual <= p1;
						when p3 =>
							estadoActual <= p2;
						when p4 =>
							estadoActual <= p3;
						when p5 =>
							estadoActual <= p4;
						when p6 =>
							estadoActual <= p5;
						when p7 =>
							estadoActual <= p6;
					end case;
				end if;
			end if;
		end if;
	end process maquinaEstadosMotor;
	---------------------------------------------
	-- Lógica secuencia motor
	---------------------------------------------
	secuenciaMotor : process(reset,estadoActual)				-- Aquí voy a manejar las sucencias
	begin
		if (reset = '0') then
			case estadoActual is
				when p0 =>
					motor0 <= '0';
					motor1 <= '0';
					motor2 <= '0';
					motor3 <= '1';
				when p1 =>
					motor0 <= '0';
					motor1 <= '0';
					motor2 <= '1';
					motor3 <= '1';
				when p2 =>
					motor0 <= '0';
					motor1 <= '0';
					motor2 <= '1';
					motor3 <= '0';
				when p3 =>
					motor0 <= '0';
					motor1 <= '1';
					motor2 <= '1';
					motor3 <= '0';
				when p4 =>
					motor0 <= '0';
					motor1 <= '1';
					motor2 <= '0';
					motor3 <= '0';
				when p5 =>
					motor0 <= '1';
					motor1 <= '1';
					motor2 <= '0';
					motor3 <= '0';
				when p6 =>
					motor0 <= '1';
					motor1 <= '0';
					motor2 <= '0';
					motor3 <= '0';
				when p7 =>
					motor0 <= '1';
					motor1 <= '0';
					motor2 <= '0';
					motor3 <= '1';
				when others =>
					motor0 <= '0';
					motor1 <= '0';
					motor2 <= '0';
					motor3 <= '0';
			end case;
		end if;
	end process secuenciaMotor;
end Behavioral;