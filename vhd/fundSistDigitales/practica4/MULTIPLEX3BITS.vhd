----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    11:53:36 09/10/2011 
-- Design Name: 
-- Module Name:    MULTIPLEX3BITS - Behavioral 
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
use IEEE.NUMERIC_STD.ALL;

-- Uncomment the following library declaration if instantiating
-- any Xilinx primitives in this code.
--library UNISIM;
--use UNISIM.VComponents.all;

entity multiplex3bits is
    Port ( SELECTOR : in  STD_LOGIC;
           JUEGO : in  STD_LOGIC_VECTOR (2 downto 0);
           SWITCH : in  STD_LOGIC_VECTOR (2 downto 0);
           DISPLAY: out  STD_LOGIC_VECTOR (2 downto 0));
end multiplex3bits;

architecture Behavioral of multiplex3bits is

begin

with SELECTOR select

	DISPLAY <= JUEGO when '1',
				  SWITCH when '0',
				  "---" when others;


end Behavioral;

