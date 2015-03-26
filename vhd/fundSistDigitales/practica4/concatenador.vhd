----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    17:49:36 09/11/2011 
-- Design Name: 
-- Module Name:    concatenador - Behavioral 
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

entity concatenador is
    Port ( sig_n : out  STD_LOGIC_VECTOR (2 downto 0);
           ent1 : in  STD_LOGIC;
           ent2 : in  STD_LOGIC;
           ent3 : in  STD_LOGIC);
end concatenador;

architecture Behavioral of concatenador is

begin
 sig_n(0) <= ent3;
 sig_n(1) <= ent2;
 sig_n(2) <= ent1;

end Behavioral;

