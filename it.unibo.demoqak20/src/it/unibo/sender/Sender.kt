/* Generated by AN DISI Unibo */ 
package it.unibo.sender

import it.unibo.kactor.*
import alice.tuprolog.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
	
class Sender ( name: String, scope: CoroutineScope  ) : ActorBasicFsm( name, scope ){

	override fun getInitialState() : String{
		return "s0"
	}
	@kotlinx.coroutines.ObsoleteCoroutinesApi
	@kotlinx.coroutines.ExperimentalCoroutinesApi			
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						forward("msg1", "msg1(1)" ,"demo" ) 
						delay(300) 
						forward("msg1", "msg1(2)" ,"demo" ) 
						delay(300) 
						forward("msg2", "msg2(1)" ,"demo" ) 
					}
				}	 
			}
		}
}
