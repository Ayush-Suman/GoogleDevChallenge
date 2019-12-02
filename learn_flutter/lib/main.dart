import 'package:flutter/material.dart';
main()=>MyApp;

class MyApp extends StatelessWidget{

  build(context){
    return MaterialApp(
      home: Scaffold(
      body: Column(
          children:<Widget>[ FlatButton(child: Text("Profile", style: TextStyle(color: Colors.white,),),
          onPressed: null,
          padding:const EdgeInsets.fromLTRB(10.0, 10.0, 10.0, 0.0),
            color: Color.fromARGB(255,0,0,100),),]
      ),),);
  }

}
