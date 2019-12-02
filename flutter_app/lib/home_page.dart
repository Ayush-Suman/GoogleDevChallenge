import 'package:flutter/material.dart';
import 'package:flutter_app/Dialog.dart';
class HomePage extends StatefulWidget {
  @override
  _MyHomePageState createState() =>new _MyHomePageState();

}
List<int> player1;
List<int> player2;
int activeplayer;
 class gameButton{
  int id;
  String text;
  Color bg;
  bool pressed;
  gameButton({this.id, this.text="", this.bg= Colors.grey, this.pressed=false});
 }




 class _MyHomePageState extends State<HomePage>{

  List<gameButton> button;
  List<gameButton> doInit(){
    activeplayer=1;
    player1= new List();
    player2= new List();
     var gameButtons= <gameButton>[new gameButton(id:1),
       new gameButton(id:2),
       new gameButton(id:3),
       new gameButton(id:4),
       new gameButton(id:5),
       new gameButton(id:6),
       new gameButton(id:7),
       new gameButton(id:8),
       new gameButton(id:9)];
     return gameButtons;
   }

   @override
   void initState(){
     super.initState();
     button=doInit();
   }
    @override
  Widget build(BuildContext context){
    reset() {

    setState(() {
      button=doInit();
      if(Navigator.canPop(context))Navigator.pop(context);
    });
    }

    void winner(){
        void win(){
          showDialog(context: context,
              builder: (_)=>new Dialoog("You won!", "Press the reset button", reset));
        }

        void lose(){
          showDialog(context: context,
              builder: (_)=>new Dialoog("You lost!", "Press the reset button", reset));
        }


        void tie(){
          showDialog(context: context,
              builder: (_)=>new Dialoog("Game tied!", "Press the reset button", reset));
        }

        if(player1.contains(1)&&player1.contains(2)&&player1.contains(3)){
          win();
        }

        else if(player1.contains(1)&&player1.contains(4)&&player1.contains(7)){
          win();
        }
        else if(player1.contains(1)&&player1.contains(5)&&player1.contains(9)){
          win();
        }
        else if(player1.contains(2)&&player1.contains(5)&&player1.contains(8)){
          win();
        }

        else if(player1.contains(3)&&player1.contains(6)&&player1.contains(9)){
          win();
        }
        else if(player1.contains(3)&&player1.contains(5)&&player1.contains(7)){
          win();
        }
        else if(player1.contains(4)&&player1.contains(5)&&player1.contains(6)){
          win();
        }
        else if(player1.contains(7)&&player1.contains(8)&&player1.contains(9)){
          win();
        }
        else if(player2.contains(1)&&player2.contains(2)&&player2.contains(3)){
          lose();
        }

        else if(player2.contains(1)&&player2.contains(4)&&player2.contains(7)){
          lose();
        }
        else if(player2.contains(1)&&player2.contains(5)&&player2.contains(9)){
          lose();
        }
        else if(player2.contains(2)&&player2.contains(5)&&player2.contains(8)){
          lose();
        }

        else if(player2.contains(3)&&player2.contains(6)&&player2.contains(9)){
          lose();
        }
        else if(player2.contains(3)&&player2.contains(5)&&player2.contains(7)){
          lose();
        }
        else if(player2.contains(4)&&player2.contains(5)&&player2.contains(6)){
          lose();
        }
        else if(player2.contains(7)&&player2.contains(8)&&player2.contains(9)){
          lose();
        }
        else if(button.every((p)=>p.text=="")){
          tie();}
      }
      void play(gameButton gb) {
      setState(() {
        if (activeplayer == 1) {
          gb.bg = Colors.red;
          player1.add(gb.id);
          gb.text = "X";
          activeplayer = 2;
          {for(int i=0;i<=8;i++) {
            if (button[i].text == "") {
              play(button[i]);
              return;
            }}
          }
        } else {
          gb.bg = Colors.black;
          player2.add(gb.id);
          gb.text = "O";
          activeplayer = 1;
        }
      });
      gb.pressed = true;
      winner();
      }



    return new Scaffold(
          appBar: new AppBar(
            title: new Text("Tic Tac Toe"),),
          body: new Column(
              mainAxisAlignment: MainAxisAlignment.start,
              crossAxisAlignment: CrossAxisAlignment.stretch,
              children:<Widget>[
               new Expanded(
                   child: new GridView.builder(
              padding: const EdgeInsets.all(9.0),
              itemCount: 9,
              gridDelegate: new SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 3,
                childAspectRatio: 1.0,
                crossAxisSpacing: 9.0,
                mainAxisSpacing: 9.0
              ),
              itemBuilder: (context,i)=> new SizedBox(
              width: 100.0,
                height: 100.0,
              child: new RaisedButton(
                onPressed: !button[i].pressed?()=>play(button[i]):null,
                padding: null,
                child: new Text(button[i].text,
                    style: new TextStyle(color: Colors.white, fontSize: 20.0)),
                color:button[i].bg,
              disabledColor: button[i].bg,),
          ))),
              new RaisedButton(onPressed: reset,
              child: new Text("Reset",
      style: new TextStyle(color: Colors.white, fontSize: 20.0)),
      color:Colors.red,)]));
    }
}
