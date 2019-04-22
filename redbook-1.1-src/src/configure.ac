AC_INIT(test_spline, 2.02.3, daniel.p.pflager@boeing.com)
AC_CONFIG_MACRO_DIR([m4])
AC_CONFIG_AUX_DIR(config)
AC_CONFIG_FILES([Makefile])
AC_CONFIG_SRCDIR(hello.c)

AM_INIT_AUTOMAKE([foreign -Wall -Werror])
AM_PROG_AR

AC_PROG_CC([gcc icc])
AC_PROG_CC_STDC

LT_INIT
AC_PROG_LIBTOOL

CPPFLAGS="$CPPFLAGS"
LDFLAGS="$LDFLAGS"
CFLAGS="-fopenmp -std=gnu99"

AC_STDC_HEADERS

AC_CHECK_LIB([m],[main],,
    AC_MSG_ERROR(["Cannot find standard math library: m"]),
    )

AC_CHECK_LIB([pthread], [pthread_create],,
    AC_MSG_ERROR(["Cannot find pthread."]),
	)

#AC_CHECK_LIB([gomp], [omp_set_lock],,
#    AC_MSG_ERROR(["Can not find gomp."]),
#    )

#AC_CHECK_LIB([mkl_intel_ilp64], [stpmv_],,
#	AC_MSG_ERROR(["Cannot find mkl_intel_ilp64."]),
#	[-lmkl_core -lmkl_gnu_thread])

#AC_CHECK_LIB([mkl_core], [mkl_lapack_dormhr],,
#	AC_MSG_ERROR(["Cannot find mkl_core."]),
#	[-lmkl_intel_ilp64 -lmkl_gnu_thread])

#AC_CHECK_LIB([mkl_gnu_thread], [mkl_spblas_lp64_czeros],,
#	AC_MSG_ERROR(["Can not find mkl_gnu_thread."]),
#	[-lmkl_core -lmkl_intel_ilp64])

#AC_CHECK_LIB([mkl_def],[mkl_blas_dasum],,
#    AC_MSG_ERROR(["Cannot find MKL def library."]),
#    )

#AC_CHECK_LIB([iges],[iaff_FormatFile],,
#    AC_MSG_ERROR(["Cannot link to ITI iges library."]),
#	[-lpdx -lgdx]
#	)

#AC_CHECK_LIB([step],[igdx_EnableIgesToGdxMappings],,
#    AC_MSG_ERROR(["Cannot link to ITI step library."]),
#	[-lgdx -lpdx]
#	)

#AC_CHECK_LIB([gdx],[eval_curv],,
#    AC_MSG_ERROR(["Cannot link to ITI gdx library."]),
#	[-lpdx]
#	)

#AC_CHECK_LIB([pdx],[cfg_FormatFile],,
#    AC_MSG_ERROR(["Cannot link to ITI pdx library."]),
#	[]
#	)

#AC_CHECK_LIB([socs],[adj2lm_],,
#    AC_MSG_ERROR(["Cannot find socs library."]),
#    )
    
#AC_DEFINE(HAVE_LIBOPTLIB)

#AC_CHECK_LIB([anvil],[nsplsq],,
#    AC_MSG_ERROR(["Cannot find anvil library."]),
#    [-lspline -lsocs] 
#    )

#AC_CHECK_LIB([spline],[spoint],,
#    AC_MSG_ERROR(["Cannot find spline library."]),
#    [-lanvil -lsocs] 
#	)

AC_OUTPUT
