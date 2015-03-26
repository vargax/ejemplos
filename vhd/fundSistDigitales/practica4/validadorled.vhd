----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:53:11 09/11/2011 
-- Design Name: 
-- Module Name:    validadorled - Behavioral 
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

entity validadorled is
    Port ( fijas_all : in  STD_LOGIC_VECTOR (2 downto 0);
           led : out  STD_LOGIC);
end validadorled;

architecture Behavioral of validadorled is

begin
with fijas_all select

led <= '0' when "111",
		 '1' when others;

end Behavioral;

