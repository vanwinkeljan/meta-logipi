#
# This file was derived from the 'Hello World!' example recipe in the
# Yocto Project Development Manual.
#

DESCRIPTION = "Logi-pi example applications"
SECTION = "devel/examples"
LICENSE = "MIT"
# Use default MIT license file as logi-tools repo does not specify any license file
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
PR = "r0"

COMPATIBLE_MACHINE = "raspberrypi"

SRCREV = "e9a8cd5f6e6f8d77023f8e79a87d2eda8d981928"
SRC_URI = "git://github.com/fpga-logi/logi-apps.git;protocol=https;branch=logipi"

S = "${WORKDIR}/git"

RDEPENDS_${PN} += "logi-tools"

do_configure[noexec] = "1"
do_compile[noexec] = "1"

sharedir = "/usr/share/"

do_install() {
  #install -d ${D}${bindir}
  #install -d ${D}${libdir}
  #install -d ${D}${includedir}
  install -d ${D}${sharedir}/${PN}/blink_led_app
  install -m 0644 -T blink_led_app/logipi_blink.bit ${D}${sharedir}/${PN}/blink_led_app/logipi_blink.bit
  #install -m 0755 logi_loader ${D}${bindir}
  #install -m 0444 c/logilib.h ${D}${includedir}
  #install -m 0644 -T liblogipi.so ${D}${libdir}/liblogipi.so.0.0
  #ln -s liblogipi.so.0.0 ${D}/${libdir}/liblogipi.so.0
  #ln -s liblogipi.so.0.0 ${D}/${libdir}/liblogipi.so
}

FILES_${PN} = "${sharedir}"

