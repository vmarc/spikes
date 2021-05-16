import { Country } from './country';
import { NetworkType } from './network-type';

export interface SubsetInfo {
  readonly country: Country;
  readonly networkType: NetworkType;
  readonly networkCount: number;
  readonly factCount: number;
  readonly changesCount: number;
  readonly orphanNodeCount: number;
  readonly orphanRouteCount: number;
}
