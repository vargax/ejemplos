----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    19:25:25 11/02/2011 
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
    Port ( entrada0 : in  STD_LOGIC_VECTOR (7 downto 0);
           entrada1 : in  STD_LOGIC_VECTOR (7 downto 0);
           salida : out  STD_LOGIC_VECTOR (7 downto 0);
           selector : in  STD_LOGIC);
end mux;

architecture Behavioral of mux is

begin
	process(entrada0, entrada1, selector)
	begin
		if selector = '0' then
			salida <= entrada0;
		else
			salida <= entrada1;
		end if;
	end process;
end Behavioral;

