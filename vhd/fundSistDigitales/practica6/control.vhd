----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    20:32:36 10/23/2011 
-- Design Name: 
-- Module Name:    control - Behavioral 
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
use ieee.std_logic_unsigned.all;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity control is
	Port ( 	reset 	: in  STD_LOGIC;
				clock		: in 	STD_LOGIC;
				est0		: in 	STD_LOGIC;
				est1		: in 	STD_LOGIC;
				est2		: in 	STD_LOGIC;
				est3		: in 	STD_LOGIC;
				est4		: in 	STD_LOGIC;
				est5		: in 	STD_LOGIC;
				est6		: in 	STD_LOGIC;
				est7		: in 	STD_LOGIC;
				motor0	: out STD_LOGIC;
				motor1	: out STD_LOGIC;
				motor2	: out STD_LOGIC;
				motor3	: out STD_LOGIC;
				parlante	: out STD_LOGIC
	);
end control;

architecture Behavioral of control is
	type estadosMotor is (p0,p1,p2,p3,p4,p5,p6,p7);
	signal estadoMotor 		: estadosMotor;
	signal contadorMotor 	: std_logic_vector(4 downto 0);
	signal avance				: std_logic;
	signal cuantasEstaciones: std_logic_vector(2 downto 0);
	signal derecha				: std_logic;
	signal estacionSensor	: std_logic_vector(2 downto 0);
	signal estacionActual	: std_logic_vector(2 downto 0);
begin
	
	-- Calculadora de estaciones
	process(clock,reset,estacionActual,estacionSensor,derecha,cuantasEstaciones,contadorMotor)
	begin
		if (reset = '0') then
			avance				<= '0';
			estacionActual		<=(others=>'0');
			cuantasEstaciones	<=(others=>'0');
			derecha 				<= '0';
		elsif (clock'event and clock = '1') then
			if (estacionActual = estacionSensor) then
				--cuantasEstaciones <=(others=>'0');
				avance		<= '0';
			else
				if (cuantasEstaciones = 0) then
					cuantasEstaciones <=  estacionSensor - estacionActual;
					if (estacionSensor - estacionActual > 4) then 
						derecha <= '0';
					else 
						derecha <= '1';
					end if;
					avance <= '1';
				elsif (contadorMotor = "0110") then		--*** NUM PASOS ENTRE ESTACIONES 1 DE 2
					if (derecha = '1') then	
						cuantasEstaciones <= cuantasEstaciones - 1;
						estacionActual 	<= estacionActual + 1;
					else 
						cuantasEstaciones <= cuantasEstaciones + 1;
						estacionActual 	<= estacionActual - 1;
					end if;
				end if;
			end if;
		end if;
	end process;
	-- Sensor
	process(clock,reset,estacionActual,estacionSensor,est0,est1,est2,est3,est4,est5,est6,est7)
	begin
		if (reset = '0') then
			estacionSensor <= "000";
		elsif (clock'event and clock = '1') then
			if (estacionActual = estacionSensor) then
				if 	(est0 = '0') then estacionSensor <= "000";
				elsif (est1 = '0') then estacionSensor <= "001";
				elsif (est2 = '0') then estacionSensor <= "010";
				elsif (est3 = '0') then estacionSensor <= "011";
				elsif (est4 = '0') then estacionSensor <= "100";
				elsif (est5 = '0') then estacionSensor <= "101";
				elsif (est6 = '0') then estacionSensor <= "110";
				elsif (est7 = '0') then estacionSensor <= "111";
				end if;
			end if;
		end if;
	end process;			
	-- Maquina de estados
	process(clock,reset,estadoMotor,contadorMotor,derecha,avance)
	begin
		if (reset = '0') then
			estadoMotor		<= p0;
			contadorMotor	<=(others=>'0');
			parlante 		<= '0';
		elsif (clock'event and clock = '1') then
			if (avance = '0' or contadorMotor = "0110") then --*** NUM PASOS ENTRE ESTACIONES 2 DE 2
				contadorMotor <=(others=>'0');
				parlante <= '0';
			else
				parlante 		<= '1';
				contadorMotor 	<= contadorMotor + 1;
				if (derecha = '1') then
					case estadoMotor is
						when p0 =>
							estadoMotor <= p1;
						when p1 =>
							estadoMotor <= p2;
						when p2 =>
							estadoMotor <= p3;
						when p3 =>
							estadoMotor <= p4;
						when p4 =>
							estadoMotor <= p5;
						when p5 =>
							estadoMotor <= p6;
						when p6 =>
							estadoMotor <= p7;
						when p7 =>
							estadoMotor <= p0;
					end case;
				else
					case estadoMotor is
						when p0 =>
							estadoMotor <= p7;
						when p1 =>
							estadoMotor <= p0;
						when p2 =>
							estadoMotor <= p1;
						when p3 =>
							estadoMotor <= p2;
						when p4 =>
							estadoMotor <= p3;
						when p5 =>
							estadoMotor <= p4;
						when p6 =>
							estadoMotor <= p5;
						when p7 =>
							estadoMotor <= p6;
					end case;
				end if;
			end if;
		end if;
	end process;
	-- Secuencia motor
	process(reset,estadoMotor)
	begin
		if (reset = '0') then
			motor0 		<= '0';
			motor1 		<= '0';
			motor2 		<= '0';
			motor3 		<= '0';
		else
			case estadoMotor is
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
	end process;
end Behavioral;

