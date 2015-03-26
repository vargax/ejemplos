----------------------------------------------------------------------------------
-- Company:
-- Engineer:
-- 
-- Create Date:    18:40:29 09/02/2011
-- Design Name:
-- Module Name:    practica3 - Behavioral
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

entity practica3 is
     Port ( LED1 : in  STD_LOGIC;
            LED2 : in  STD_LOGIC;
            LED3 : in  STD_LOGIC;
            LED4 : in  STD_LOGIC;
            LED5 : in  STD_LOGIC;
            LED6 : in  STD_LOGIC;
            LED7 : in  STD_LOGIC;
            SEGA : out  STD_LOGIC;
            SEGB : out  STD_LOGIC;
            SEGC : out  STD_LOGIC;
            SEGD : out  STD_LOGIC;
            SEGE : out  STD_LOGIC;
            SEGF : out  STD_LOGIC;
            SEGG : out  STD_LOGIC);
end practica3;

architecture Behavioral of practica3 is

begin
     SEGA <= (LED1 AND LED2 AND LED3 AND NOT LED4) OR (NOT LED1 AND NOT LED3 AND LED4);
     SEGB <= (LED2 AND NOT LED3 AND NOT LED4) OR (NOT LED1 AND NOT LED2 AND NOT LED3 AND LED4);
     SEGC <= LED1 AND LED2 AND NOT LED3 AND LED4;
     SEGD <= (LED1 AND LED2 AND LED3 AND NOT LED4) OR (NOT LED1 AND LED2 AND NOT LED3 AND LED4);
     SEGE <= (LED1 AND LED2 AND LED3 AND NOT LED4) OR (NOT LED1 AND LED2 AND NOT LED3);
     SEGF <= LED1;
     SEGG <= LED3;
end Behavioral;