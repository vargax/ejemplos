----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    21:36:09 09/10/2011 
-- Design Name: 
-- Module Name:    sumador - Behavioral 
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

entity sumador is
    Port ( num : in  STD_LOGIC_VECTOR (2 downto 0);
           suma : out  STD_LOGIC_VECTOR (2 downto 0));
end sumador;

architecture Behavioral of sumador is

begin

with num select
	suma <= "001" when "000",
			  "010" when "001",
			  "011" when "010",
			  "100" when "011",
			  "101" when "100",
			  "110" when "101",
			  "111" when "110",
			  "000" when "111",
			  "---" when others;
				

end Behavioral;

