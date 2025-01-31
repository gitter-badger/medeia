package medeia.generic.semiauto

import medeia.BsonCodec
import medeia.decoder.BsonDecoder
import medeia.encoder.BsonDocumentEncoder
import medeia.generic.{GenericDecoder, GenericEncoder}
import shapeless.Lazy

trait Semiauto {
  def deriveEncoder[A](implicit genericEncoder: Lazy[GenericEncoder[A]]): BsonDocumentEncoder[A] = genericEncoder.value

  def deriveDecoder[A](implicit genericDecoder: Lazy[GenericDecoder[A]]): BsonDecoder[A] = genericDecoder.value

  def deriveCodec[A, H](implicit
                        genericEncoder: GenericEncoder[A],
                        genericDecoder: GenericDecoder[A]): BsonCodec[A] = {
    BsonCodec.fromEncoderAndDecoder
  }
}
