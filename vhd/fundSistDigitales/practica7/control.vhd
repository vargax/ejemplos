----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    07:16:12 11/02/2011 
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
use IEEE.std_logic_unsigned.all;
use ieee.std_logic_arith.all;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity control is
	Port(	clk 				: in  STD_LOGIC;
         reset 			: in  STD_LOGIC;
			inicioIn			: in  STD_LOGIC;
         filasBolita 	: in  STD_LOGIC_VECTOR (7 downto 0);
			columnasJugador: in  STD_LOGIC_VECTOR (7 downto 0);
         columnasBolita : in  STD_LOGIC_VECTOR (7 downto 0);
			visible			: out STD_LOGIC;
			inicioOut		: out STD_LOGIC;
			puntajeOut		: out	STD_LOGIC_VECTOR (3 downto 0);
			filas				: out STD_LOGIC_VECTOR (7 downto 0);
			columnas			: out STD_LOGIC_VECTOR (7 downto 0)
	);
end control;

architecture Behavioral of control is
	type estados is (inicio, jugando, victoria, derrota);
	signal estado : estados;
	signal puntaje : STD_LOGIC_VECTOR (3 downto 0);
begin
	process(clk, reset, inicioIn, filasBolita, columnasJugador, columnasBolita, estado, puntaje)
	begin
		if reset = '1' then
			estado 		<= inicio;
			puntaje 		<= "0000";
			visible		<= '0';
			inicioOut	<= '1';
			puntajeOut	<= "0000";
			filas			<= "00000000";
			columnas		<= "00000000";
		elsif clk'event and clk = '1' then
			case estado is
				when inicio =>
					inicioOut<= '1';
					visible	<= '0';
					puntaje	<= "0000";
					if (inicioIn = '1') then
						estado <= jugando;
					end if;
				when jugando =>
					inicioOut<= '0';
					visible	<= '0';
					if filasBolita = "10000000" then
						if columnasJugador = columnasBolita then
							if (puntaje = 9) then
								estado <= victoria;
							else
								puntaje 		<= puntaje + 1;
								puntajeOut	<= puntaje + 1;
							end if;
						else
							estado <= derrota;
						end if;
					end if;
				when victoria =>
					inicioOut	<= '1';
					visible		<= '1';
					filas			<= "10000001";
					columnas		<= "11111111";
					if inicioIn = '1' then
						estado <= inicio;
					end if;
				when derrota =>
					inicioOut	<= '1';
					visible		<= '1';
					filas			<= "11111111";
					columnas		<= "11111111";
					if inicioIn = '1' then
						estado <= inicio;
					end if;
			end case;
		end if;
	end process;
end Behavioral;

