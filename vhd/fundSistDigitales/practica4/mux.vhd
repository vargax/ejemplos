----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    22:07:16 09/10/2011 
-- Design Name: 
-- Module Name:    mux - Behavioral 
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

entity mux is
    Port ( sel : in  STD_LOGIC;
           n : in  STD_LOGIC_VECTOR (2 downto 0);
           nsum : in  STD_LOGIC_VECTOR (2 downto 0);
           salida : out  STD_LOGIC_VECTOR (2 downto 0));
end mux;

architecture Behavioral of mux is

begin

	with sel select
	 salida <= n when '0',
	        nsum when '1',
			  "---" when others;


end Behavioral;

