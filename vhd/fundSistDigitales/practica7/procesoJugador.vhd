----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    11:37:51 10/29/2011 
-- Design Name: 
-- Module Name:    procesoJugador - Behavioral 
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
--use ieee.std_logic_unsigned.all;
-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity procesoJugador is
	Port(	reset			: in  STD_LOGIC;
			clk			: in  STD_LOGIC;
			derecha 		: in  STD_LOGIC;
			izquierda 	: in  STD_LOGIC;
			filasOut		: out STD_LOGIC_VECTOR (7 downto 0);
			columnasOut	: out STD_LOGIC_VECTOR (7 downto 0)
	);
end procesoJugador;

architecture Behavioral of procesoJugador is
	type estados is (listo,der,izq,presionado);
	signal estado	: estados;
	signal filas	: STD_LOGIC_VECTOR (7 downto 0);
	signal columnas: STD_LOGIC_VECTOR (7 downto 0);
begin
	process(reset,clk,estado,filas,columnas)
	begin
		if reset='1' then
			columnas <= "00001000";
			filas		<= "10000000";
		elsif clk'event and clk = '1' then
			case estado is
				when der =>
					columnas(7) <= columnas(0);
					columnas(6 downto 0) <= columnas(7 downto 1);
				when izq	=>
					columnas(7 downto 1) <= columnas(6 downto 0);
					columnas(0) <= columnas(7);
				when others =>
					columnas <= columnas;
			end case;
		end if;	
	end process;
	
	process(reset,clk,estado,derecha,izquierda)
	begin
		if reset = '1' then
			estado <= listo;
		elsif clk'event and clk = '1' then
			if estado = listo and derecha = '1' then
				estado <= der;
			elsif estado = listo and izquierda = '1' then
				estado <= izq;
			elsif estado = izq or estado = der then
				estado <= presionado;
			elsif estado = presionado and izquierda = '0' and derecha = '0' then
				estado <= listo;
			end if;
		end if;
	end process;
	
	process(reset,clk,filas,columnas)
	begin
		if reset = '1' then
			filasOut 	<= "10000000";
			columnasOut	<= "00001000";
		elsif clk'event and clk = '1' then
			filasOut 	<= filas;
			columnasOut <= columnas;
		end if;
	end process;
end Behavioral;