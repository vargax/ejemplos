----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    19:49:20 09/11/2011 
-- Design Name: 
-- Module Name:    multiplex5bits - Behavioral 
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

entity multiplex5bits is
    Port ( SELECTOR : in  STD_LOGIC;
           JUEGO : in  STD_LOGIC_VECTOR (4 downto 0);
           SWITCH : in  STD_LOGIC_VECTOR (2 downto 0);
           DISPLAY: out  STD_LOGIC_VECTOR (4 downto 0));
end multiplex5bits;

architecture Behavioral of multiplex5bits is

begin
process(SELECTOR,JUEGO,SWITCH)
begin
if(SELECTOR='1') then DISPLAY <= JUEGO;
else
DISPLAY(4) <= SWITCH(2);
DISPLAY(3) <= SWITCH(1);
DISPLAY(2) <= SWITCH(0);
DISPLAY(1) <= '1';
DISPLAY(0) <= '1';
end if;
end process;


end Behavioral;

