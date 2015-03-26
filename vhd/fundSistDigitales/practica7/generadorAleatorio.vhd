----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    15:27:54 10/31/2011 
-- Design Name: 
-- Module Name:    generadorAleatorio - Behavioral 
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

-- Uncomment the following library declaration if using
-- arithmetic functions with Signed or Unsigned values
--use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity generadorAleatorio is
	Port (	clk 		: in  STD_LOGIC;
				reset		: in  STD_LOGIC;
				derecha 	: in  STD_LOGIC;
				izquierda: in  STD_LOGIC;
				inicioOut: out  STD_LOGIC_VECTOR (7 downto 0)
	);
end generadorAleatorio;

architecture Behavioral of generadorAleatorio is
	signal inicio : STD_LOGIC_VECTOR (7 downto 0);
begin
	process(clk,reset,derecha,izquierda)
	begin
		if reset = '1' then
			inicio <= "00000001";
		elsif clk'event and clk = '1' then
			if derecha = '1' or izquierda = '1' then
				inicio(7) <= inicio(0);
				inicio(6 downto 0) <= inicio(7 downto 1);
			else
				inicio(7 downto 1) <= inicio(6 downto 0);
				inicio(0) <= inicio(7);
			end if;
		end if;
	end process;
	
	process(clk,reset,inicio)
	begin
		if reset = '1' then
			inicioOut <= "00000001";
		elsif clk'event and clk = '1' then
			inicioOut <= inicio;
		end if;
	end process;
end Behavioral;