----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    11:51:15 09/10/2011 
-- Design Name: 
-- Module Name:    MULTIPLEXOR3BITS - Behavioral 
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

entity MULTIPLEXOR3BITS is
    Port ( DIS1 : in  STD_LOGIC_VECTOR (0 downto 2);
           DIS2 : in  STD_LOGIC_VECTOR (0 downto 2);
           DIS3 : in  STD_LOGIC_VECTOR (0 downto 2);
           SW1 : in  STD_LOGIC_VECTOR (0 downto 2);
           SW2 : in  STD_LOGIC_VECTOR (0 downto 2);
           SW3 : in  STD_LOGIC_VECTOR (0 downto 2));
end MULTIPLEXOR3BITS;

architecture Behavioral of MULTIPLEXOR3BITS is

begin


end Behavioral;

