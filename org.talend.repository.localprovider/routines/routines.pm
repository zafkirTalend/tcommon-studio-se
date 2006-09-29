# user define

use Exporter;

use vars qw( @EXPORT @ISA ) ;    

@ISA = qw(Exporter);
                                  
                             
@EXPORT = qw(
              $max
              rule1
              ereplace
              npad
              myf
              mextract
              
              getDate
            );


$max = 4000;

sub rule1
{                                      
 my ($addr) = (@_);

 my $extr = mextract($addr);

 if ($extr > $max) { return 1 } else { return 0 }
}


sub myf
{
  my ($reg) = (@_);
  $reg =~ m/(\d+)-(\d+)-(\d+)/;         
  
  return "$3:$2:$1";
}

sub mextract
{
 my ($addr) = (@_);

 $addr =~ m/(\d+)/;                   
 return $1;
}


sub npad
{
  my ($num, $len) = (@_);

  return sprintf("%0" . $len . "d", $num);
}


sub ereplace
{
  my ($pstring, $psubstring, $preplacement, $pnumber, $pbegin) = (@_);
  my $mresult;

  $mresult = $pstring;

  $mresult =~ s/$psubstring/$preplacement/g;

  return $mresult;
}

# getDate : return the current datetime with the given display format
#
# format : (optional) string representing the wished format of the
#          date. This string contains fixed strings and variables related
#          to the date. By default, the format string is DD/MM/CCYY. Here
#          is the list of date variables:
#
#    + CC for century
#    + YY for year
#    + MM for month
#    + DD for day
#    + hh for hour
#    + mm for minute
#    + ss for second
sub getDate {
    my ($format) = @_;
    $format = 'DD/MM/CCYY' if not defined $format;

    my ($sec,$min,$hour,$mday,$mon,$year,$wday,$yday,$isdst) =
        localtime(time);

    my %fields = (
        CC => int(($year + 1900) / 100),
        YY => $year % 100,
        MM => $mon + 1,
        DD => $mday,
        hh => $hour,
        mm => $min,
        ss => $sec
    );

    %fields = map {$_ => sprintf('%02u', $fields{$_})} keys %fields;

    foreach my $field (keys %fields) {
        $format =~ s/$field/$fields{$field}/g;
    }

    return $format;
}

1;
