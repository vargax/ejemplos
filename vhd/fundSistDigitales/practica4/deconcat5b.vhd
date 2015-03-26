----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    20:12:52 09/11/2011 
-- Design Name: 
-- Module Name:    deconcat5b - Behavioral 
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

entity deconcat5b is
    Port ( sig : in  STD_LOGIC_VECTOR (4 downto 0);
           A2 : out  STD_LOGIC;
           A1 : out  STD_LOGIC;
           A0 : out  STD_LOGIC;
           RBI : out  STD_LOGIC;
           RBO : out  STD_LOGIC);
end deconcat5b;

architecture Behavioral of deconcat5b is

begin
A2 <= sig(4);
A1 <= sig(3);
A0 <= sig(2);
RBI <= sig(1);
RBO <= sig(0);

end Behavioral;

