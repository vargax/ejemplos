----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    20:09:11 09/11/2011 
-- Design Name: 
-- Module Name:    deconcat3b - Behavioral 
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

entity deconcat3b is
    Port ( sig : in  STD_LOGIC_VECTOR (2 downto 0);
           A2 : out  STD_LOGIC;
           A1 : out  STD_LOGIC;
           A0 : out  STD_LOGIC);
end deconcat3b;

architecture Behavioral of deconcat3b is

begin
A0 <= sig(0);
A1 <= sig(1);
A2 <= sig(2);


end Behavioral;

