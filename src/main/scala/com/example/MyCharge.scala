package com.example


import io.circe._
import io.circe.generic.extras.Configuration
import io.circe.generic.extras.decoding.UnwrappedDecoder._
import io.circe.generic.extras.encoding.UnwrappedEncoder._
import io.circe.generic.extras.semiauto._
import io.circe._
import io.circe.generic.extras.semiauto.{deriveDecoder, deriveEncoder}
import io.circe.generic.extras.Configuration

case class MyCustomer (
                      id: MyCustomer.Id,
                      name: MyCustomer.Name,
                      email: MyCustomer.Email
                      )
object MyCustomer {
  case class Id(value: String) extends AnyVal
  case class Name(value: String) extends AnyVal
  case class Email(value: String) extends AnyVal

  implicit val circeConfig: Configuration =
    Configuration.default.withSnakeCaseMemberNames.withDefaults

  implicit val encoder: Encoder[MyCustomer] = deriveEncoder[MyCustomer]
  implicit val decoder: Decoder[MyCustomer] = deriveDecoder[MyCustomer]
}
