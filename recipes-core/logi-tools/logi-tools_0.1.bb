#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "Logi-pi loader and spi lib)"
SECTION = "console/utils"
LICENSE = "MIT"
# Use default MIT license file as logi-tools repo does not specify any license file
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r0"

COMPATIBLE_MACHINE = "raspberrypi"

SRCREV = "a02d33f40163bf55bc7babc55aec415c1fdb5e52"
SRC_URI = "git://github.com/fpga-logi/logi-tools.git;protocol=https;branch=master"

S = "${WORKDIR}/git"

do_compile() {
  ${CC} logipi_loader/serial_fpga_loader.c -o logi_loader
  ${CC} -shared -Wall -Werror -fpic -o liblogipi.so c/logipilib.c
}

do_install() {
  install -d ${D}${bindir}
  install -d ${D}${libdir}
  install -d ${D}${includedir}
  install -m 0755 logi_loader ${D}${bindir}
  install -m 0444 c/logilib.h ${D}${includedir}
  install -m 0644 -T liblogipi.so ${D}${libdir}/liblogipi.so.0.0
  ln -s liblogipi.so.0.0 ${D}/${libdir}/liblogipi.so.0
  ln -s liblogipi.so.0.0 ${D}/${libdir}/liblogipi.so
}
