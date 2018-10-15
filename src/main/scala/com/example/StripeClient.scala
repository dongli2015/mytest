package com.example

import com.stripe.Stripe
import com.stripe.model.Charge

import scala.collection.JavaConversions._
import scala.util.Try


trait StripeClient {
  def getAllCharges: Seq[Charge] = {
    val chargeParams = new java.util.HashMap[String, AnyRef]()
    chargeParams.put("limit", "10")
    Charge.list(chargeParams).autoPagingIterable.toSeq
  }

  def createCharge(amount: Int): Charge = {
    val chargeParams = new java.util.HashMap[String, AnyRef]()
    val amountInteger: AnyRef = Int.box(amount)
    val capture: AnyRef = Boolean.box(false)
    chargeParams.put("amount", amountInteger)
    chargeParams.put("currency", "jpy")
    chargeParams.put("description", "test charges")
    chargeParams.put("source", "tok_mastercard")
    chargeParams.put("capture", capture)
    Charge.create(chargeParams)
  }

  def getCharge(id: String): Option[Charge] = {
    Try(Charge.retrieve(id)).toOption
  }

  def fullCaptureCharge(id: String): Option[Charge] = {
    getCharge(id).map(charge => charge.capture())
  }
}

object TestClient extends StripeClient {
  Stripe.apiKey = "sk_test_Vob5TEZxiS9HoZCPqSdOTWrf"

}

object LiveClient extends StripeClient {
  Stripe.apiKey = ""
}
