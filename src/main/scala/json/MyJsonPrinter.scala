package json

import spray.json.{JsArray, JsBoolean, JsNull, JsNumber, JsObject, JsString, JsValue, PrettyPrinter}

trait MyJsonPrinter extends PrettyPrinter {

  def myJson2SprayJson(json: JsonValue): JsValue = ???

  def apply(json: JsonValue): String = apply(myJson2SprayJson(json))
}
object MyJsonPrinter extends MyJsonPrinter
