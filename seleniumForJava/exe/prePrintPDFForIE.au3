#region --- Au3Recorder generated code Start (v3.3.9.5 KeyboardLayout=00000411)  ---

#region --- Internal functions Au3Recorder Start ---
Func _Au3RecordSetup()
Opt('WinWaitDelay',100)
Opt('WinDetectHiddenText',1)
Opt('MouseCoordMode',0)

#Local $aResult = DllCall('User32.dll', 'int', 'GetKeyboardLayoutNameW', 'wstr', '')
#If $aResult[1] <> '00000411' Then
#  MsgBox(64, 'Warning', 'Recording has been done under a different Keyboard layout' & @CRLF & '(00000411->' & $aResult[1] & ')')
#EndIf

EndFunc

Func _WinWaitActivate($title,$text,$timeout=0)
	WinWait($title,$text,$timeout)
	If Not WinActive($title,$text) Then WinActivate($title,$text)
	WinWaitActive($title,$text,$timeout)
EndFunc

_AU3RecordSetup()
#endregion --- Internal functions Au3Recorder End ---

_WinWaitActivate("印刷","ページ番号のみか、またはページ範囲のみを")
#MouseClick("left",452,220,1)
ControlFocus("印刷","ページ番号のみか、またはページ範囲のみを","1010")
ControlClick("印刷","詳細設定","Button4")

_WinWaitActivate("印刷設定","Layout Picture")
ControlFocus("印刷設定","Layout Picture","1232")
$CALC = WinGetHandle("印刷設定")
WinActivate($CALC)
$Value = "横"
$index = ControlCommand($CALC,"Find String","[CLASS:ComboBox; INSTANCE:1]","FindString",$Value )
ControlCommand($CALC, "", "[CLASS:ComboBox; INSTANCE:1]", "SetCurrentSelection", $index)

ControlClick("印刷設定","詳細設定","Button8")
#MouseClick("left",131,108,1)
#MouseClick("left",73,130,1)
#MouseMove(448,417)
#MouseDown("left")
#MouseMove(448,416)
#MouseUp("left")

_WinWaitActivate("Microsoft Print To PDF 詳細オプション","キャンセル")
ControlFocus("Microsoft Print To PDF 詳細オプション","キャンセル","9060")
$CALC = WinGetHandle("Microsoft Print To PDF 詳細オプション")
WinActivate($CALC)
$Value = "A3"
$index = ControlCommand($CALC,"Find String","[CLASS:ComboBox; INSTANCE:1]","FindString",$Value )
ControlCommand($CALC, "", "[CLASS:ComboBox; INSTANCE:1]", "SetCurrentSelection", $index)

ControlFocus("Microsoft Print To PDF 詳細オプション","キャンセル","1")
ControlClick("Microsoft Print To PDF 詳細オプション","OK","Button2")

#MouseClick("left",220,93,1)
#MouseClick("left",168,116,1)
#MouseMove(321,455)
#MouseDown("left")
#MouseMove(320,454)
#MouseUp("left")
_WinWaitActivate("印刷設定","Layout Picture")
ControlFocus("印刷設定","キャンセル","1")
ControlClick("印刷設定","OK","Button9")

#MouseClick("left",380,456,1)

_WinWaitActivate("印刷","ページ番号のみか、またはページ範囲のみを")
ControlFocus("印刷","ページ番号のみか、またはページ範囲のみを","1")
ControlClick("印刷","印刷","Button13")
#MouseMove(307,451)
#MouseDown("left")
#MouseMove(311,451)
#MouseUp("left")
_WinWaitActivate("印刷結果を名前を付けて保存","Namespace Tree Contr")
ControlFocus("印刷結果を名前を付けて保存","text","1001")
WinWait("[CLASS:;32770]","",100)
ControlSetText("印刷結果を名前を付けて保存","","[CLASS:Edit; INSTANCE:1]",$CmdLine[1])
Sleep(1000)
ControlClick("印刷結果を名前を付けて保存","保存","Button2")
#endregion --- Au3Recorder generated code End ---
