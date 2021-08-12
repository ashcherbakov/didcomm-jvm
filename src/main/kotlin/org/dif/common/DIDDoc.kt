package org.dif.common

/**
 * DID DOC (https://www.w3.org/TR/did-core/#dfn-did-documents)
 * @property did                    a DID for the given DID Doc
 * @property keyAgreements          Key IDs (DID URLs) of all verification methods from the 'keyAgreement'
 *                                  verification relationship in this DID DOC.
 *                                  See https://www.w3.org/TR/did-core/#verification-methods.
 * @property authentications        Key IDs (DID URLs) of all verification methods from the 'authentication'
 *                                  verification relationship in this DID DOC.
                                    See https://www.w3.org/TR/did-core/#authentication.
 * @property verificationMethods    Returns all local verification methods including embedded
 *                                  to key agreement and authentication sections.
 *                                  See https://www.w3.org/TR/did-core/#verification-methods.
 * @property didCommServices        All services of 'DIDCommMessaging' type in this DID DOC.
 *                                  Empty list is returned if there are no services of 'DIDCommMessaging' type.
 *                                  See https://www.w3.org/TR/did-core/#services and https://identity.foundation/didcomm-messaging/spec/#did-document-service-endpoint.
 */
data class DIDDoc(
    val did: String,
    val keyAgreements: List<String>,
    val authentications: List<String>,
    val verificationMethods: List<VerificationMethod>,
    val didCommServices: List<DIDCommService>
)

/**
 * DID DOC Verification method.
 * It can be used in such verification relationships as Authentication, KeyAgreement, etc.
 * See https://www.w3.org/TR/did-core/#verification-methods.
 */
data class VerificationMethod(
    val id: String,
    val type: String,
    val controller: String,
    val verificationMaterial: VerificationMaterial,
)

data class VerificationMaterial(
    val type: EncodingType,
    val encodedValue: String
)

enum class EncodingType {
    JWK,
    BASE58,
    OTHER
}

/**
 * DID DOC Service of 'DIDCommMessaging' type.
 * see https://www.w3.org/TR/did-core/#services
 * and https://identity.foundation/didcomm-messaging/spec/#did-document-service-endpoint.
 */
data class DIDCommService(
    val id: String,
    val serviceEndpoint: String,
    val routingKeys: String
)