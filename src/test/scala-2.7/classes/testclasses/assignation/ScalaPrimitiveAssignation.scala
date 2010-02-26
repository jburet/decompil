package testclasses.assignation

class ScalaPrimitiveAssignation {
	  
  def constantIntAssignation() = {
    var i = 0
    i
  }
  
  def constantBooleanAssignation() = {
    var i = true
    i
  }
  
  def constantLongAssignation() = {
    var i = 999999999999l
    i
  }
  
  def constantFloatAssignation() = {
    var i = 2.5f
    i
  }
  
  def constantDoubleAssignation() = {
    var i = 2.5d
    i
  }
  
  def constantCharAssignation() = {
    var i = 'a'
    i
  }  
  
  def varIntAssignation(variable:Int) = {
    var i = variable
    i
  }
}
