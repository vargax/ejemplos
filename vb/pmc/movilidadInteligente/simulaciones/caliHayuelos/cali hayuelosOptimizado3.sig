<?xml version="1.0" encoding="UTF-8"?>
<sc id="3" name="" frequency="1" steps="0" defaultIntergreenMatrix="0">
  <sgs>
    <sg id="1" name="3,0;2,1:1,0" defaultSignalSequence="3">
      <defaultDurations />
    </sg>
    <sg id="2" name="2,-1;3,0:2,1:1,0" defaultSignalSequence="3">
      <defaultDurations />
    </sg>
    <sg id="3" name="1,0;3,0" defaultSignalSequence="3">
      <defaultDurations />
    </sg>
  </sgs>
  <intergreenmatrices />
  <progs>
    <prog id="1" cycletime="60000" switchpoint="0" offset="0" intergreens="0" fitness="0.000000" vehicleCount="0" name="Signal program 1">
      <sgs>
        <sg sg_id="1" signal_sequence="1">
          <cmds>
            <cmd display="1" begin="0" />
          </cmds>
          <fixedstates />
        </sg>
        <sg sg_id="2" signal_sequence="1">
          <cmds>
            <cmd display="1" begin="0" />
          </cmds>
          <fixedstates />
        </sg>
        <sg sg_id="3" signal_sequence="1">
          <cmds>
            <cmd display="1" begin="0" />
          </cmds>
          <fixedstates />
        </sg>
      </sgs>
    </prog>
  </progs>
  <stages />
  <interstageProgs />
  <stageProgs />
  <dailyProgLists />
</sc>