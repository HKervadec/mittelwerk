/*makefile non functional*/


CPPFLAGS= -shared -o
LDINCLUDE= -IOrbitersdk\include
LDFLAGS= -g
LDLIBS=-LOrbitersdk\lib -lDlgCtrl.lib -lGDIClient.lib -lorbiter.lib -lOrbitersdk.lib -LOrbitersdk\lib\Lua -llua5.1.lib -llua51.lib

ShuttleA: ShuttleA.h Otto.h
	g++ $(CPPFLAGS) ShuttleA.dll ShuttleA.cpp Otto.cpp $(LDINCLUDE) $(LDLIBS)
	
