----------------------------------------------------------------------------------
-- Company: 
-- Engineer: 
-- 
-- Create Date:    16:36:09 09/11/2011 
-- Design Name: 
-- Module Name:    fijas - Behavioral 
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

entity fijas is
    Port ( sw1 : in  STD_LOGIC_VECTOR (2 downto 0);
           sw2 : in  STD_LOGIC_VECTOR (2 downto 0);
           sw3 : in  STD_LOGIC_VECTOR (2 downto 0);
           nu1 : in  STD_LOGIC_VECTOR (2 downto 0);
           nu2 : in  STD_LOGIC_VECTOR (2 downto 0);
           nu3 : in  STD_LOGIC_VECTOR (2 downto 0);
           fijas : out  STD_LOGIC_VECTOR (2 downto 0);
			  led : out STD_LOGIC);

end fijas;

architecture Behavioral of fijas is

begin

 fijas(0) <= ((sw1(0) xnor nu1(0))and(sw1(1) xnor nu1(1))and(sw1(2) xnor nu1(2)));
 fijas(1) <= ((sw2(0) xnor nu2(0))and(sw2(1) xnor nu2(1))and(sw2(2) xnor nu2(2)));
 fijas(2) <= ((sw3(0) xnor nu3(0))and(sw3(1) xnor nu3(1))and(sw3(2) xnor nu3(2)));
 led <= '0' when (((sw1(0) xnor nu1(0))and(sw1(1) xnor nu1(1))and(sw1(2) xnor nu1(2)))and
					  ((sw2(0) xnor nu2(0))and(sw2(1) xnor nu2(1))and(sw2(2) xnor nu2(2)))and
					  ((sw3(0) xnor nu3(0))and(sw3(1) xnor nu3(1))and(sw3(2) xnor nu3(2))))= '1'
					  else '1';


end Behavioral;

