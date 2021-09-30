package org.dif.jmh

import org.dif.DIDComm
import org.dif.fixtures.BenchCommon
import org.dif.fixtures.JWM
import org.dif.mock.AliceSecretResolverMock
import org.dif.mock.DIDDocResolverMock
import org.dif.model.PackSignedParams
import org.openjdk.jmh.annotations.Benchmark
import org.openjdk.jmh.annotations.Scope
import org.openjdk.jmh.annotations.Setup
import org.openjdk.jmh.annotations.State
import org.openjdk.jmh.infra.Blackhole

@State(Scope.Benchmark)
open class DIDCommBenchPackSigned {

    @State(Scope.Benchmark)
    open class Data {
        public lateinit var didComm: DIDComm

        @Setup
        fun setup() {
            didComm = DIDComm(DIDDocResolverMock(), AliceSecretResolverMock())
        }
    }

    @Benchmark
    fun packSigned(bh: Blackhole, d: Data) {
        bh.consume(
            d.didComm.packSigned(
                PackSignedParams.builder(JWM.PLAINTEXT_MESSAGE, JWM.ALICE_DID).build()
            )
        )
    }

    @Benchmark
    fun packSignedV2(bh: Blackhole/*, d: Data*/) {
        bh.consume(
            BenchCommon.packSigned()
        )
    }
}
