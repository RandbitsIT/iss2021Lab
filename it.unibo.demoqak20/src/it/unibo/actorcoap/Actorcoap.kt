/* Generated by AN DISI Unibo */ 
package it.unibo.actorcoap

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Actorcoap ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						println("Please activate a CoAP client observer (e.g. actorQakCoapObserver.kt)")
						updateResourceRep( "actorcoap init done"  
						)
						delay(1000) 
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("work") { //this:State
					action { //it:State
						updateResourceRep( "actorcoap is ready to work"   
						)
					}
					 transition(edgeName="t00",targetState="handleCmd",cond=whenDispatch("cmd"))
					transition(edgeName="t01",targetState="handleRequest",cond=whenRequest("cmd"))
				}	 
				state("handleCmd") { //this:State
					action { //it:State
						println("$name in ${currentState.stateName} | $currentMsg")
						if( checkMsgContent( Term.createTerm("cmd(X)"), Term.createTerm("cmd(V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								 val V = payloadArg(0)  
								println("actorcoap is handling dispatch cmd($V)")
								updateResourceRep( "actorcoap is handling dispatch cmd($V)"  
								)
						}
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
				state("handleRequest") { //this:State
					action { //it:State
						if( checkMsgContent( Term.createTerm("cmd(X)"), Term.createTerm("cmd(V)"), 
						                        currentMsg.msgContent()) ) { //set msgArgList
								  val V      = payloadArg(0)
								 				val Answer = "answerTo_${V}" 
								println("actorcoap is handling request cmd($V)")
								updateResourceRep( "actorcoap is handling request cmd($V)"  
								)
								answer("cmd", "cmdansw", "cmdansw($Answer)"   )  
						}
					}
					 transition( edgeName="goto",targetState="work", cond=doswitch() )
				}	 
			}
		}
}