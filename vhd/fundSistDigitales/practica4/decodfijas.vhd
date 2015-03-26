----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    19:16:34 09/11/2011 
-- Design Name: 
-- Module Name:    decodfijas - Behavioral 
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

entity decodfijas is
    Port ( rawn : in  STD_LOGIC_VECTOR (2 downto 0);
           display3 : out  STD_LOGIC_VECTOR (4 downto 0);
           numfijas : out  STD_LOGIC_VECTOR (2 downto 0));
end decodfijas;

architecture Behavioral of decodfijas is

begin

display3 <= "00000";

with rawn select

	
	
	numfijas <= "000" when "000",
					"001" when "001",
					"001" when "010",
					"001" when "100",
					"010" when "011",
					"010" when "101",
					"010" when "110",
					"011" when "111",
					"---" when others;


end Behavioral;
