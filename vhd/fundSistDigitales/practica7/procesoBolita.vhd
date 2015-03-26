----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:48:05 10/30/2011 
-- Design Name: 
-- Module Name:    procesoBolita - Behavioral 
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

entity procesoBolita is
   Port ( 	clk 			: in  STD_LOGIC;
				reset 		: in  STD_LOGIC;
				inicio		: in  STD_LOGIC_VECTOR (7 downto 0);
				filasOut		: out STD_LOGIC_VECTOR (7 downto 0);
				columnasOut	: out STD_LOGIC_VECTOR (7 downto 0)
	);
end procesoBolita;

architecture Behavioral of procesoBolita is
	signal filas 	: STD_LOGIC_VECTOR (7 downto 0);
	signal columnas: STD_LOGIC_VECTOR (7 downto 0);
begin
	process(clk,reset,inicio,filas)
	begin
		if reset = '1' then
			filas 	<= "10000000";
			columnas <= "00000000";
		elsif clk'event and clk = '1' then
			filas(7 downto 1) <= filas(6 downto 0);
			filas(0) <= filas(7);
			if (filas = "10000000") then
				columnas <= inicio;
			end if;
		end if;
	end process;
	
	process(reset,clk,filas,columnas)
	begin
		if reset = '1' then
			filasOut 	<= "00000001";
			columnasOut	<= "00000000";
		elsif clk'event and clk = '1' then
			filasOut 	<= filas;
			columnasOut <= columnas;
		end if;
	end process;
end Behavioral;