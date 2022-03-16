#!/bin/csh -f
# ----------------------------------------------------------------
# This script calculates the Meridional Overturning Circulation (MOC) and Heat Transport
# from the monthly mean velocities in a monthly mean high resolution 0.1 degree POP file (pop_file)
# It makes a so called MSF file (output_file) containing the following fields:

# MHTG = mean meridional heat transport (global) in PetaWatts
# MHTA =  mean meridional heat transport (atlantic) in PetaWatts
# MHTIP = mean meridional heat transport (indo pacific) in PetaWatts

# TMTG = meridional overturning streamfunction (global) in Sv
# TMTA = meridional overturning streamfunction (atlantic) in Sv
# TMTIP = meridional overturning streamfunction (indo pacific) in Sv

set pop_file = rcp8.5_co2_f05_t12.pop.h.2000-01.nc       # just an example of a tripolar high resolution 0.1 degree (tx0.1) POP file  
set output_file = MSF_rcp8.5_co2_f05_t12.pop.h.2000-01.nc

set kmt_file = 'topography_200709.ieeei4'                 # ocean depth level (kmt) for each gridpoint 
set kmt_atl_file = 'basin_mask_kmt.atl'                   # ocean depth level (kmt) for atlantic gridpoints
set kmt_indopac_file = 'basin_mask_kmt.indopac'           # ocean depth level (kmt) for indopacific gridpoints
set in_depths_file = 'in_depths.42.dat'                   # thickness (in cm) of each depth level 
set grid_file = 'horiz_grid_200709.ieeer8'                # horizontal grid 
set pbc_file = 'dzbc_pbc_s2.0_200709.ieeer8'              # thickness partial bottom cells

# ----------------------------------------------------------------
# Don't change anything below 
# the *_first_record fields can be ignored they are only needed for binary (not netcdf) POP files
# ----------------------------------------------------------------

cat > in_msf << EOF
      &msf_stuff
      imt = 3600
      jmt = 2400
      km = 42
      ysouth_mht = -75.
      ynorth_mht = 75.
      dy_mht = 0.5
      kmt_file = '$kmt_file'
      kmt_atl_file = '$kmt_atl_file'
      kmt_indopac_file = '$kmt_indopac_file'
      in_depths_file = '$in_depths_file'
      grid_file = '$grid_file'
      pbc_file = '$pbc_file'
      u_first_record  = 1
      v_first_record = 43
      uet_first_record  = 217
      vnt_first_record = 259
      output_file = '$output_file'
      verbose = .true.
      do_msf = .true.
      do_mht = .true.
      u_file = '$pop_file'
      v_file = '$pop_file'
      uet_file = '$pop_file'
      vnt_file = '$pop_file'
/
EOF

./calculate_msf_cesm < in_msf


